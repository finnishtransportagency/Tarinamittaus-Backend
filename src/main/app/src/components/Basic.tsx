// Render Prop
import React from 'react';
import { Formik, Form, Field, ErrorMessage } from 'formik';
import * as Yup from "yup";
import IMittaus from '../types/interfaces/mittaus.interface';
import { observer } from 'mobx-react';
import MittausStore from '../stores/MittausStore';


const Basic = observer( ({mittaus}: {mittaus: MittausStore}) =>
  <div>
    <Formik
      initialValues={{ ...mittaus }}
      onSubmit={(
        values: IMittaus
        ) => {
          alert(JSON.stringify(values, null, 2));
      }}
      validationSchema={Yup.object().shape({
        email: Yup.string()
          .email()
          .required("Required")
      })}
    >
      {props => {
        const {
          values,
          touched,
          errors,
          dirty,
          isSubmitting,
          handleChange,
          handleBlur,
          handleSubmit,
          handleReset
        } = props;
        return (
          <form onSubmit={handleSubmit}>
            <label htmlFor="alkauaika" style={{ display: "block" }}>
              Mittauksen alkuaika
            </label>
            <input
              id="alkuaika"
              placeholder=""
              type=""
              value={values.alkuaika}
              onChange={handleChange}
              onBlur={handleBlur}
              className={
                errors.alkuaika && touched.alkuaika
                  ? "text-input error"
                  : "text-input"
              }
            />
            <label htmlFor="loppuaika" style={{ display: "block" }}>
              Mittauksen loppuaika
            </label>
            <input
              id="loppuaika"
              placeholder=""
              type="text"
              value={values.loppuaika}
              onChange={handleChange}
              onBlur={handleBlur}
              className={
                errors.loppuaika && touched.loppuaika
                  ? "text-input error"
                  : "text-input"
              }
            />
            <label htmlFor="mittaus_asianhallinta_id" style={{ display: "block" }}>
              Mittauspyynnön asianhallintatunnus
            </label>
            <input
              id="mittaus_asianhallinta_id"
              placeholder=""
              type="text"
              value={values.mittaus_asianhallinta_id}
              onChange={handleChange}
              onBlur={handleBlur}
              className={
                errors.mittaus_asianhallinta_id && touched.mittaus_asianhallinta_id
                  ? "text-input error"
                  : "text-input"
              }
            />
            <label htmlFor="pdf_raportin_linkki" style={{ display: "block" }}>
              Lisää mittausraportin linkki
            </label>
            <input
              id="pdf_raportin_linkki"
              placeholder=""
              type="text"
              value={values.pdf_raportin_linkki}
              onChange={handleChange}
              onBlur={handleBlur}
              className={
                errors.pdf_raportin_linkki && touched.pdf_raportin_linkki
                  ? "text-input error"
                  : "text-input"
              }
            />
            <label htmlFor="pdf_raportin_linkki" style={{ display: "block" }}>
              Lisää mittausraportin linkki
            </label>
            <input
              id="pdf_raportin_linkki"
              placeholder=""
              type="text"
              value={values.pdf_raportin_linkki}
              onChange={handleChange}
              onBlur={handleBlur}
              className={
                errors.pdf_raportin_linkki && touched.pdf_raportin_linkki
                  ? "text-input error"
                  : "text-input"
              }
            />
          {/* tähän anturi komponentit */}
            <label htmlFor="rakennuksen_pinta_ala" style={{ display: "block" }}>
              Rakennuksen pinta-ala
            </label>
            <input
              id="rakennuksen_pinta_ala"
              placeholder=""
              type="text"
              value={values.rakennuksen_pinta_ala}
              onChange={handleChange}
              onBlur={handleBlur}
              className={
                errors.rakennuksen_pinta_ala && touched.rakennuksen_pinta_ala
                  ? "text-input error"
                  : "text-input"
              }
            />
            <label htmlFor="julkisivumateriaali" style={{ display: "block" }}>
              Julkisivumateriaali
            </label>
            <input
              id="julkisivumateriaali"
              placeholder=""
              type="text"
              value={values.julkisivumateriaali}
              onChange={handleChange}
              onBlur={handleBlur}
              className={
                errors.julkisivumateriaali && touched.julkisivumateriaali
                  ? "text-input error"
                  : "text-input"
              }
            />
            <label htmlFor="runkomateriaali" style={{ display: "block" }}>
              Runkomateriaali
            </label>
            <input
              id="runkomateriaali"
              placeholder=""
              type="text"
              value={values.runkomateriaali}
              onChange={handleChange}
              onBlur={handleBlur}
              className={
                errors.runkomateriaali && touched.runkomateriaali
                  ? "text-input error"
                  : "text-input"
              }
            />
            <label htmlFor="perustamistapa" style={{ display: "block" }}>
              Perustamistapa
            </label>
            <input
              id="perustamistapa"
              placeholder=""
              type="text"
              value={values.perustamistapa}
              onChange={handleChange}
              onBlur={handleBlur}
              className={
                errors.perustamistapa && touched.perustamistapa
                  ? "text-input error"
                  : "text-input"
              }
            />
            <label htmlFor="rakennusvuosi" style={{ display: "block" }}>
              Rakennusvuosi
            </label>
            <input
              id="rakennusvuosi"
              placeholder=""
              type="text"
              value={values.rakennusvuosi}
              onChange={handleChange}
              onBlur={handleBlur}
              className={
                errors.rakennusvuosi && touched.rakennusvuosi
                  ? "text-input error"
                  : "text-input"
              }
            />
            <label htmlFor="katuosoite" style={{ display: "block" }}>
              Katuosoite
            </label>
            <input
              id="katuosoite"
              placeholder=""
              type="text"
              value={values.katuosoite}
              onChange={handleChange}
              onBlur={handleBlur}
              className={
                errors.katuosoite && touched.katuosoite
                  ? "text-input error"
                  : "text-input"
              }
            />
            <label htmlFor="postinumero" style={{ display: "block" }}>
              Postinumero
            </label>
            <input
              id="postinumero"
              placeholder=""
              type="text"
              value={values.postinumero}
              onChange={handleChange}
              onBlur={handleBlur}
              className={
                errors.postinumero && touched.postinumero
                  ? "text-input error"
                  : "text-input"
              }
            />


            <button
              type="button"
              className="outline"
              onClick={handleReset}
              disabled={!dirty || isSubmitting}
            >
              Reset
            </button>
            <button type="submit" disabled={isSubmitting}>
              Submit
            </button>

            <DisplayFormikState {...props} />
          </form>
        );
      }}
    </Formik>
  </div>
);
export default Basic;

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