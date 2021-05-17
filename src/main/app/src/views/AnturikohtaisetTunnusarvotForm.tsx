import { Field, Formik } from 'formik'
import React from 'react'
import { CustomNumber } from '../components/CustomNumber'
// import { AnturikohtaisetTunnusarvotStore } from '../stores/AnturikohtaisetTunnusarvotStore'
import MittausSuuntaTypeEnum from '../types/enums/mittausSuuntaType.enum'
import * as Yup from 'yup';
import { IAnturikohtaisetTunnusarvo } from '../types/interfaces/anturikohtaisetTunnusarvot.interface'
import { Col, Row, Form, Container } from 'react-bootstrap';


const AnturikohtaisetTunnusarvotForm = ({ tunnusarvot, namespace }: { tunnusarvot: IAnturikohtaisetTunnusarvo[], namespace: string }) => {
  console.log('AnturikohtaisetTunnusarvotForm', namespace);
  return (
    <div key={namespace}>
      <h4>Tärinän tunnusluvut ja huippuarvot</h4>
      <p>Suunta x on radansuuntainen</p>
      <p>Suunta y on kohtisuoraan rataan nähden</p>
      <p>Suunta z on pystysuuntainen värähtely</p>

      <Container>
        <Row>
          {tunnusarvot.map((tunnusarvo, index) => (
            <div key={index}>
              <Col>
                <Form.Label >Hallitseva taajuus Hz {tunnusarvo.mittaussuunta_xyz}-akseli</Form.Label>
                <Field
                  label='Hallitseva taajuus'
                  type="number"
                  name={`${namespace}.${index}.hallitseva_taajuus`}
                  readOnly={false}
                />



                <Form.Label >Tärinän maksimiarvo mm/s</Form.Label>
                <Field
                  label='Tärinän maksimiarvo'
                  type="number"
                  name={`${namespace}.${index}.tarinan_maksimiarvo`}
                  readOnly={false}
                />




                <Form.Label >Tärinän tunnusluku mm/s Vw95_RMS</Form.Label>
                <Field
                  label='Tärinän tunnusluku vw95 rms'
                  type="number"
                  name={`${namespace}.${index}.tarinan_tunnusluku_vw95_rms`}
                  readOnly={false}
                />

              </Col>

            </div>
          ))}
        </Row>
      </Container>
    </div>
  )
}

export default AnturikohtaisetTunnusarvotForm
