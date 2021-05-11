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
import { values } from 'mobx';

const validationSchemaAsennettuAnturi = Yup.object({
  malli: Yup.string().required('Malli vaaditaan'),
  gps_lat: Yup.number().min(-90).max(90).required('Koordinaatit eivät voi olla tyhjiä'),
  gps_long: Yup.number().min(0).max(180).required('Koordinaatit eivät voi olla tyhjiä'),
  etaisyys_radasta_jos_eri: Yup.number().positive().required(),
  kerros: Yup.number().integer().positive().required(),
  sijoituspaikan_lisaselite: Yup.string()
  // asennuspaikantyyppi:
  // anturikohtaisettunnusarvot:
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
  //ghost field for checking that both cannot be null: https://github.com/jquense/yup/issues/176
  as_id_OR_pdf: Yup.bool().when(['mittaus_asianhallinta_id', 'pdf_raportin_linkki'], {
    is: (mittaus_asianhallinta_id: string, pdf_raportin_linkki: string) =>
      (!mittaus_asianhallinta_id && !pdf_raportin_linkki)
      || (!!mittaus_asianhallinta_id && !!pdf_raportin_linkki),
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
  asennettuanturi: validationSchemaAsennettuAnturi
})


const MittausForm = ({ mittaus }: { mittaus: MittausStore }) => {
  return (
    <>
      <h2>Mittauksen tiedot</h2>
      <Formik
        initialValues={{ ...mittaus }}
        validationSchema={validationSchema}
        onSubmit={(values, { setSubmitting }) => {
          setTimeout(() => {
            alert(JSON.stringify(values, null, 2));
            setSubmitting(false);
          }, 400);
        }}
      >
        {formik => (
          <FForm onSubmit={formik.handleSubmit}>
            <FormikCustomDatePicker
              label="Mittauksen alkuaika"
              name="alkauaika"
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
              name="asennettuanturi"
              render={arrayHelpers => (
                <div>
                  {formik.values.asennettuanturi && formik.values.asennettuanturi.length > 0 ? (
                    formik.values.asennettuanturi.map((anturi, index) => (
                      <div key={index}>
                        <AsennettuAnturiForm {...anturi} />
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
            <Button
              type="button"
              className="outline"
              onClick={formik.handleReset}
              disabled={!formik.dirty || formik.isSubmitting}
            >
              Reset
            </Button>
            <Button type="submit" disabled={formik.isSubmitting}>
              Submit
            </Button>
            <DisplayFormikState props={formik} />
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
        fontSize: '.65rem',
        padding: '.5rem',
      }}
    >
      <strong>props</strong> ={' '}
      {JSON.stringify(props, null, 2)}
    </pre>
  </div>;