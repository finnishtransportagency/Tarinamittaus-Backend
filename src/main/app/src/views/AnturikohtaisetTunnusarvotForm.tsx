import React from 'react';

import { IAnturikohtaisetTunnusarvo } from '../types/interfaces/anturikohtaisetTunnusarvot.interface'
import { CustomSensorNumber } from '../components/CustomSensorNumber';
import { Row, Form } from 'react-bootstrap';


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
            <CustomSensorNumber
              key={index}
              label={`Hallitseva taajuus Hz (${tunnusarvo.mittaussuunta_xyz}-akseli)`}
              name={`${namespace}.${index}.hallitseva_taajuus`}
              readOnly={false}
            />
          ))}
        </Row>
      </Form.Group>
      <Form.Group>
        <Row>
          {tunnusarvot.map((tunnusarvo, index) => (
            <CustomSensorNumber
              key={index}
              label={`Värähtelyn huippuarvo mm/s (${tunnusarvo.mittaussuunta_xyz}-akseli)`}
              name={`${namespace}.${index}.tarinan_maksimiarvo`}
              readOnly={false}
            />
          ))}
        </Row>
      </Form.Group>
      <Form.Group>
        <Row>
          {tunnusarvot.map((tunnusarvo, index) => (
            <CustomSensorNumber
              key={index}
              label={`Tärinän tunnusluku mm/s Vw95_RMS (${tunnusarvo.mittaussuunta_xyz}-akseli)`}
              name={`${namespace}.${index}.tarinan_tunnusluku_vw95_rms`}
              readOnly={false}
            />
          ))}
        </Row>
      </Form.Group>
    </div>
  )
}

export default AnturikohtaisetTunnusarvotForm
