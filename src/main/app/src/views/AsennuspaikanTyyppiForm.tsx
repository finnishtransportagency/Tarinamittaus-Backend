import { Field, Form, Formik } from 'formik'
import React from 'react'
import AsennuspaikanTyyppiStore from '../stores/AsennuspaikanTyyppiStore'
import SeliteTypeEnum from '../types/enums/seliteType.enum'
import * as Yup from 'yup';
import { CustomText } from '../components/CustomText';


const validationSchema = Yup.object({
  selite: Yup.mixed<string>().oneOf(Object.values(SeliteTypeEnum)).required(),
  lisatiedot: Yup.string().when(['selite'], {
    is: (selite: string) => selite === SeliteTypeEnum.muu,
    then: Yup.string().required('Anna lisätiedot'),
    otherwise: Yup.string()
  })
})

const AsennuspaikanTyyppiForm = ({ paikka, namespace }: { paikka: AsennuspaikanTyyppiStore, namespace: string }) => {
  return (
    <>
      <Field name={`${namespace}.selite`} as="select">
        {Object.keys(SeliteTypeEnum).map(key =>
          <option value={key}>{key}</option>
        )}
      </Field>
      <CustomText
        label='Lisätiedot'
        name={`${namespace}lisatiedot`}
        readOnly={false}
      />
    </>
  )
}

export default AsennuspaikanTyyppiForm
