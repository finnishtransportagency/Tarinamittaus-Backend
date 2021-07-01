import React from 'react';

import { Field } from 'formik'
import { IAnturikohtaisetTunnusarvo } from '../types/interfaces/anturikohtaisetTunnusarvot.interface'
import { Col, Row, Form } from 'react-bootstrap';


const AnturikohtaisetTunnusarvotForm = ({ tunnusarvot, namespace }: { tunnusarvot: IAnturikohtaisetTunnusarvo[], namespace: string }) => {
  console.log('AnturikohtaisetTunnusarvotForm', namespace);
  return (
    <div key={namespace}>
      <h4>Tärinän tunnusluvut ja huippuarvot</h4>
      <p>Suunta x on radansuuntainen</p>
      <p>Suunta y on kohtisuoraan rataan nähden</p>
      <p>Suunta z on pystysuuntainen värähtely</p>

      <Form.Group>
        <Row>
          {tunnusarvot.map((tunnusarvo, index) => (
            <Col key={index} sm="3">
              <Form.Label >Hallitseva taajuus Hz ({tunnusarvo.mittaussuunta_xyz}-akseli)</Form.Label>
              <Field
                type="number"
                name={`${namespace}.${index}.hallitseva_taajuus`}
                readOnly={false}
              />
            </Col>
          ))}
        </Row>
      </Form.Group>
      <Form.Group>
        <Row>
          {tunnusarvot.map((tunnusarvo, index) => (
            <Col key={index} sm="3">
              <Form.Label >Värähtelyn huippuarvo mm/s ({tunnusarvo.mittaussuunta_xyz}-akseli)</Form.Label>
              <Field
                type="number"
                name={`${namespace}.${index}.tarinan_maksimiarvo`}
                readOnly={false}
              />
            </Col>
          ))}
        </Row>
      </Form.Group>
      <Form.Group>
        <Row>
          {tunnusarvot.map((tunnusarvo, index) => (
            <Col key={index} sm="3">
              <Form.Label >Tärinän tunnusluku mm/s Vw95_RMS ({tunnusarvo.mittaussuunta_xyz}-akseli)</Form.Label>
              <Field
                type="number"
                name={`${namespace}.${index}.tarinan_tunnusluku_vw95_rms`}
                readOnly={false}
              />
            </Col>
          ))}
        </Row>
      </Form.Group>
    </div>
  )
}

export default AnturikohtaisetTunnusarvotForm
