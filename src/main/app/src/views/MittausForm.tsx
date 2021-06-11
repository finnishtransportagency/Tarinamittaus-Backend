import React from 'react'
import { Formik, FieldArray as FFieldArray } from 'formik'
import * as Yup from "yup";
import MittausStore from '../stores/MittausStore'
import { FormikCustomDatePicker } from '../components/CustomDatePicker';
import { CustomNumber } from '../components/CustomNumber';
import { CustomText } from '../components/CustomText';
import AsennettuAnturiForm from './AsennettuAnturiForm';
import AsennettuAnturiStore from '../stores/AsennettuAnturiStore';
import { Form as FForm } from 'formik';
import { Button } from 'react-bootstrap';
import SeliteTypeEnum from '../types/enums/seliteType.enum';
import MittausSuuntaTypeEnum from '../types/enums/mittausSuuntaType.enum';
import { useParams } from 'react-router-dom';
import IMittaus from '../types/interfaces/mittaus.interface';
import { fullRestURL } from '../App';

const validationSchemaTunnusarvot = Yup.object({
  mittaussuunta_xyz: Yup.mixed<string>().oneOf(Object.values(MittausSuuntaTypeEnum)).required(),
  tarinan_maksimiarvo: Yup.number().min(0),
  hallitseva_taajuus: Yup.number().min(0),
  tarinan_tunnusluku_vw95_rms: Yup.number().min(0)
})


const validationSchemaAsennuspaikanTyyppi = Yup.object({
  selite: Yup.mixed<string>().oneOf(Object.values(SeliteTypeEnum)).required(),
  lisatiedot: Yup.string().when(['selite'], {
    is: ( selite: string ) => selite === SeliteTypeEnum.muu,
    then: Yup.string().required('Anna lisätiedot'),
    otherwise: Yup.string()
  })
})

const validationSchemaAsennettuAnturi = Yup.object({
  malli: Yup.string().required('Malli vaaditaan'),
  gps_lat: Yup.number().min(-90).max(90).required('Koordinaatit eivät voi olla tyhjiä'),
  gps_long: Yup.number().min(0).max(180).required('Koordinaatit eivät voi olla tyhjiä'),
  etaisyys_radasta_jos_eri: Yup.number().min(0).required(),
  kerros: Yup.number().integer().required(),
  sijoituspaikan_lisaselite: Yup.string(),
  asennuspaikanTyyppi: validationSchemaAsennuspaikanTyyppi,
  anturikohtaisetTunnusarvot: Yup.array().of(validationSchemaTunnusarvot).max(3)
})

const validationSchema = Yup.object().shape({
  alkuaika: Yup.date().required().default(() => new Date()),
  loppuaika: Yup
    .date()
    .min(
      Yup.ref('alkuaika'),
      "Loppuaika ei voi olla ennen alkuaikaa"
    )
    .required(),
  mittaus_asianhallinta_id: Yup.string().trim(),
  pdf_raportin_linkki: Yup.string().trim(),
  //ghost field for checking that both mittaus_asianhallinta_id and pdf_raportin_linkki be null: https://github.com/jquense/yup/issues/176
  as_id_OR_pdf: Yup.bool().when(['mittaus_asianhallinta_id', 'pdf_raportin_linkki'], {
    is: (mittaus_asianhallinta_id: string, pdf_raportin_linkki: string) =>
      (!mittaus_asianhallinta_id && !pdf_raportin_linkki),
    then: Yup.bool().required('Asianhallinta id tai pdf raportin linkki eivät kumpikaan voi olla tyhjiä'),
    otherwise: Yup.bool()
  }),
  rakennuksen_pinta_ala: Yup.number().positive(),
  julkisivumateriaali: Yup.string().trim(),
  runkomateriaali: Yup.string().trim(),
  perustamistapa: Yup.string().trim(),
  rakennusvuosi: Yup.number().positive().integer().min(1500).max(new Date().getFullYear()),
  katuosoite: Yup.string().trim(),
  postinumero: Yup.string().trim()
    .matches(/^[0-9]+$/, "Postinumero ei voi sisältää kirjaimia")
    .min(5, 'Täytyy olla 5 numeroa')
    .max(5, 'Täytyy olla 5 numeroa'),
  created_by_lx: Yup.string().trim().required(),
  asennettuAnturi: Yup.array().of(validationSchemaAsennettuAnturi)
})

// TODO: move api methods to different file
// const baseUrl = 'http://localhost:8080/mittaus/';
const baseUrl = fullRestURL();

const postData = async (url = '', data = {}) => {
  const response = await fetch(url, {
    method: 'POST',
    mode: 'cors',
    cache: 'no-cache',
    credentials: 'same-origin',
    headers: {
      'Content-Type': 'application/json'
    },
    redirect: 'follow',
    referrerPolicy: 'no-referrer',
    body: JSON.stringify(data)
  });
  return response.json();
}

const putData = async (data = {}) => {
  const response = await fetch(baseUrl, {
    method: 'PUT',
    mode: 'cors',
    cache: 'no-cache',
    credentials: 'same-origin',
    headers: {
      'Content-Type': 'application/json'
    },
    redirect: 'follow',
    referrerPolicy: 'no-referrer',
    body: JSON.stringify(data)
  });
  return response.json();
}

const getData = async (id: string) => {
  return fetch(baseUrl + id, {
    method: 'GET',
    mode: 'cors',
    cache: 'no-cache',
    credentials: 'same-origin',
    headers: {
      'Content-Type': 'application/json',
    },
    redirect: 'follow',
    referrerPolicy: 'no-referrer',
  }).then(data => data.json())
    .then(json => json.status && json.status !== 200 ? null : json)
    .catch(err => {
      console.error(err);
      return null;
    });
}

const deleteData = async (id: string) => {
  const response = await fetch(baseUrl + id, {
    method: 'DELETE',
    mode: 'cors',
    cache: 'no-cache',
    credentials: 'same-origin',
    headers: {
      'Content-Type': 'application/json',
    },
    redirect: 'follow',
    referrerPolicy: 'no-referrer',
  });
  return response.json();
}

const initializeEmptyFields = (mittaus: IMittaus): IMittaus => Object.entries(mittaus)
  .reduce((acc, [k, v]) => ({
  ...acc,
  [k]: v || "",
}), {} as IMittaus);

const MittausForm = ({ mittaus }: { mittaus: MittausStore }) => {
  const [fetchedValues, setFetchedValues] = React.useState<IMittaus | null>(null);
  console.log("mittausform", mittaus)
  const { id } = useParams<{ id: string }>();

  React.useEffect(() => {
    const fetchAndSetData = async () => {
      const data = await getData(id);
      if (!data) return;
      setFetchedValues(initializeEmptyFields(data));
    }
    id && fetchAndSetData();
  }, [id]);

  if (id && !fetchedValues) return <div>ei mittausta</div>;
  return (
    <>
      <h2>Mittauksen tiedot: {id ? `(kohdetunnus ${id})` : "(Uusi mittaus)"}</h2>
      <Formik
        initialValues={ fetchedValues || {
          alkuaika: '',
          loppuaika: '',
          mittaus_asianhallinta_id: '',
          pdf_raportin_linkki: '',
          rakennuksen_pinta_ala: '',
          perustamistapa: '',
          julkisivumateriaali: '',
          runkomateriaali: '',
          rakennusvuosi: '',
          katuosoite: '',
          postinumero: '',
          created_by_lx: '',
          asennettuAnturi: [],
        }}
        validationSchema={validationSchema}
        enableReinitialize
        onSubmit={(values, { setSubmitting }) => {
          console.log(JSON.stringify(values));
          postData(`${baseUrl}`, { ...values })
          .then(res => {
            console.log(res);
            console.log(res.data);
            setSubmitting(false);
          })
          .catch(err => {
            console.log(err);
            setSubmitting(false);
          })
        }}
      >
        {formik => (
          <FForm onSubmit={formik.handleSubmit}>
            <FormikCustomDatePicker
              label="Mittauksen alkuaika"
              name="alkuaika"
              readOnly={false}
            />
            <FormikCustomDatePicker
              label="Mittauksen loppuaika"
              name="loppuaika"
              readOnly={false}
            />
            <CustomText
              label="Mittauspyynnön asianhallintatunnus"
              name="mittaus_asianhallinta_id"
              readOnly={false}
            />
            <CustomText
              label="Lisää mittausraportin linkki"
              name="pdf_raportin_linkki"
              readOnly={false}
            />
            {/* tähän anturi komponentit */}
            <FFieldArray
              name="asennettuAnturi"
              render={arrayHelpers => (
                <div>
                  {formik.values.asennettuAnturi && formik.values.asennettuAnturi.length > 0 ? (
                    formik.values.asennettuAnturi.map((anturi, index) => (
                      <div key={index}>
                        <AsennettuAnturiForm asennettuAnturi={anturi} namespace={`asennettuAnturi.${index}`} />
                        <Button
                          onClick={() => arrayHelpers.remove(index)} // remove a friend from the list
                        >
                          -
                       </Button>
                        <Button
                          onClick={() => arrayHelpers.insert(index, new AsennettuAnturiStore)} // insert an empty string at a position
                        >
                          +
                       </Button>
                      </div>
                    ))
                  ) : (
                    <Button onClick={() => arrayHelpers.push(new AsennettuAnturiStore)}>
                      {/* show this when user has removed all friends from the list */}
                     Lisää anturi
                    </Button>
                  )}
                </div>
              )}
            />
            <h4>Kohdetiedot (Anturin 1 sijainnin perusteella</h4>
            <p>Mittaus voidaan tallentaa tietokantaan myös ilman rakennustietoja</p>
            <CustomText
              label="Rakennuksen pinta-ala"
              name="rakennuksen_pinta_ala"
              readOnly={false}
            />
            <CustomText
              label="Julkisivumateriaali"
              name="julkisivumateriaali"
              readOnly={false}
            />
            <CustomText
              label="Runkomateriaali"
              name="runkomateriaali"
              readOnly={false}
            />
            <CustomText
              label="Perustamistapa"
              name="perustamistapa"
              readOnly={false}
            />
            <CustomNumber
              label="Rakennusvuosi"
              name="rakennusvuosi"
              readOnly={false}
            />
            <CustomText
              label="Katuosoite"
              name="katuosoite"
              readOnly={false}
            />
            <CustomText
              label="Postinumero"
              name="postinumero"
              readOnly={false}
            />
            <CustomText
              label="created_by_lx"
              name="created_by_lx"
              readOnly={false}
            />
            <div id="button_container">
              {id ? <>
                  <Button
                      type="button"
                      variant="danger"
                      onClick={() => deleteData(id)}
                    >
                      Poista
                  </Button>
                  <Button
                      type="button"
                      variant="primary"
                      onClick={() => putData(formik.values)}
                    >
                      Päivitä
                  </Button>
                </> :
                <>
                  <Button
                    type="button"
                    className="outline"
                    onClick={formik.handleReset}
                    disabled={!formik.dirty || formik.isSubmitting}
                  >
                    Tyhjennä
                  </Button>
                  <Button type="submit" disabled={formik.isSubmitting}>
                    Lähetä
                  </Button>
                </>
              }
            </div>

            {/* <DisplayFormikState props={formik} /> */}
          </FForm>

        )}
      </Formik>
    </>
  )
}

export default MittausForm


export const DisplayFormikState = (props: any) =>
  <div style={{ margin: '1rem 0' }}>
    <h3 style={{ fontFamily: 'monospace' }} />
    <pre
      style={{
        background: '#f6f8fa',
        fontSize: '1.25rem',
        padding: '.5rem',
      }}
    >
      <strong>props</strong> ={' '}
      {JSON.stringify(props, null, 2)}
    </pre>
  </div>;