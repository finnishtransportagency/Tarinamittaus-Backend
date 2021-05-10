import { Field, Formik } from 'formik'
import React from 'react'
import { CustomNumber } from '../components/CustomNumber'
import AnturikohtaisetTunnusarvotStore from '../stores/AnturikohtaisetTunnusarvotStore'
import MittausSuuntaTypeEnum from '../types/enums/mittausSuuntaType.enum'
import * as Yup from 'yup';


const validationSchema = Yup.object({
  mittaussuunta_xyz: Yup.mixed<string>().oneOf(Object.values(MittausSuuntaTypeEnum)).required(),
  tarinan_maksimiarvo: Yup.number().positive().required(),
  hallitseva_taajuus: Yup.number().positive().required(),
  tarinan_tunnusluku_vw95_rms: Yup.number().positive().required()
})


const AnturikohtaisetTunnusarvotForm = ({ tunnusarvot }: { tunnusarvot: AnturikohtaisetTunnusarvotStore }) => {
  return (
    <>
    <h4>Tärinän tunnusluvut ja huippuarvot</h4>
      <Formik
        initialValues={{ ...tunnusarvot }}
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
            <Field name="mittaussuunta_xyz" as="select">
              {Object.keys(MittausSuuntaTypeEnum).map(key =>
                <option value={key}>{key}</option>
              )}
            </Field>
            <CustomNumber
              label='Tärinän maksimiarvo'
              name='tarinan_maksimiarvo'
              readOnly={false}
            />
            <CustomNumber
              label='Hallitseva taajuus'
              name='hallitseva_taajuus'
              readOnly={false}
            />
            <CustomNumber
              label='Tärinän tunnusluku vw95 rms'
              name='tarinan_tunnusluku_vw95_rms'
              readOnly={false}
            />
          </form>
        )}

      </Formik>


    </>
  )
}

export default AnturikohtaisetTunnusarvotForm
