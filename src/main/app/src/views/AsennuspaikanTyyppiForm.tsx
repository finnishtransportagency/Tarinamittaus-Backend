import { Field, Formik } from 'formik'
import React from 'react'
import AsennuspaikanTyyppiStore from '../stores/AsennuspaikanTyyppiStore'
import SeliteTypeEnum from '../types/enums/seliteType.enum'
import * as Yup from 'yup';
import { CustomText } from '../components/CustomText';


const validationSchema = Yup.object({
  selite: Yup.mixed<string>().oneOf(Object.values(SeliteTypeEnum)).required(),
  lisatiedot: Yup.string().when(['selite'], {
    is: ( selite: string ) => selite === SeliteTypeEnum.muu,
    then: Yup.string().required('Anna lisätiedot'),
    otherwise: Yup.string()
  })
})

const AsennuspaikanTyyppiForm = ({ paikka }: { paikka: AsennuspaikanTyyppiStore }) => {
  return (
    <>
      <Formik
        initialValues={{ ...paikka }}
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
            <Field name="selite" as="select">
              {Object.keys(SeliteTypeEnum).map(key =>
                <option value={key}>{key}</option>
              )}
            </Field>
            <CustomText
              label='Lisätiedot'
              name='lisatiedot'
              readOnly={false}
            />
          </form>
        )}
      </Formik>
    </>
  )
}

export default AsennuspaikanTyyppiForm
