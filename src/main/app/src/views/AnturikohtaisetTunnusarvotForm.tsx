import { Field, Form, Formik } from 'formik'
import React from 'react'
import { CustomNumber } from '../components/CustomNumber'
import AnturikohtaisetTunnusarvotStore from '../stores/AnturikohtaisetTunnusarvotStore'
import MittausSuuntaTypeEnum, { SuuntaTestiConst } from '../types/enums/mittausSuuntaType.enum'
import * as Yup from 'yup';


const validationSchema = Yup.object({
  mittaussuunta_xyz: Yup.mixed<string>().oneOf(Object.values(MittausSuuntaTypeEnum)).required(),
  tarinan_maksimiarvo: Yup.number().positive().required(),
  hallitseva_taajuus: Yup.number().positive().required(),
  tarinan_tunnusluku_vw95_rms: Yup.number().positive().required()
})


const AnturikohtaisetTunnusarvotForm = ({ tunnusarvot, namespace }: { tunnusarvot: AnturikohtaisetTunnusarvotStore, namespace: string }) => {
  console.log('AnturikohtaisetTunnusarvotForm');
  return (
    <>
      <h4>Tärinän tunnusluvut ja huippuarvot</h4>
      <p>Suunta x on radansuuntainen</p>
      <p>Suunta y on kohtisuoraan rataan nähden</p>
      <p>Suunta z on pystysuuntainen värähtely</p>

      <Field name={`${namespace}.mittaussuunta_xyz`} as="select">
        {Object.keys(MittausSuuntaTypeEnum).map(key =>
          <option value={key}>{key}</option>
        )}
      </Field>
      <CustomNumber
        label='Tärinän maksimiarvo'
        name={`${namespace}.tarinan_maksimiarvo`}
        readOnly={false}
      />
      <CustomNumber
        label='Hallitseva taajuus'
        name={`${namespace}.hallitseva_taajuus`}
        readOnly={false}
      />
      <CustomNumber
        label='Tärinän tunnusluku vw95 rms'
        name={`${namespace}.tarinan_tunnusluku_vw95_rms`}
        readOnly={false}
      />
    </>
  )
}

export default AnturikohtaisetTunnusarvotForm
