import React from 'react'
import { Formik } from 'formik'
import * as Yup from "yup";
import MittausStore from '../stores/MittausStore'
import { FormikCustomDatePicker } from '../components/CustomDatePicker';
import { CustomNumber } from '../components/CustomNumber';
import { CustomText } from '../components/CustomText';


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
  as_id_OR_pdf: Yup.bool().when(['mittaus_asianhallinta_id','pdf_raportin_linkki'], {
    is: (mittaus_asianhallinta_id: string, pdf_raportin_linkki: string) =>
      (!mittaus_asianhallinta_id && !pdf_raportin_linkki)
       || (!!mittaus_asianhallinta_id && !!pdf_raportin_linkki),
    then: Yup.bool().required('Asianhallinta id tai pdf raportin linkki eivät kumpikaan voi olla tyhjiä'),
    otherwise: Yup.bool()
  }),
  rakennuksen_pinta_ala: Yup.number().positive().required(),
  perustamistapa: Yup.string().trim().required(),
  julkisivumateriaali: Yup.string().trim().required(),
  runkomateriaali: Yup.string().trim().required(),
  rakennusvuosi: Yup.number().positive().integer().min(1500).max(new Date().getFullYear()).required(),
  katuosoite: Yup.string().trim().required(),
  postinumero: Yup.string().trim().required()
    .matches(/^[0-9]+$/, "Postinumero ei voi sisältää kirjaimia")
    .min(5, 'Täytyy olla 5 numeroa')
    .max(5, 'Täytyy olla 5 numeroa'),
  created_by_lx: Yup.string().trim().required()
})


const MittausForm = ({ mittaus }: { mittaus: MittausStore }) => {
  return (
    <>
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
          <form onSubmit={formik.handleSubmit}>
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
            <button
              type="button"
              className="outline"
              onClick={formik.handleReset}
              disabled={!formik.dirty || formik.isSubmitting}
            >
              Reset
            </button>
            <button type="submit" disabled={formik.isSubmitting}>
              Submit
            </button>
            <DisplayFormikState props={formik} />
          </form>

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