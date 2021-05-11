import { Form } from "formik";
import { observer } from "mobx-react";
import * as Yup from 'yup';
import { CustomNumber } from "../components/CustomNumber";
import { CustomText } from "../components/CustomText";
import AsennettuAnturiStore from "../stores/AsennettuAnturiStore";



const AsennettuAnturiForm = ({ asennettuAnturi, namespace }: { asennettuAnturi: AsennettuAnturiStore, namespace: string }) => {
  return (
    <>
      <h3>Anturien ja anturikohtaisten arvojen lisääminen</h3>
      <Form>
        <CustomNumber
          label="gps_lat"
          name={`${namespace}.gps_lat`}
          readOnly={false}
        />
        <CustomNumber
          label="gps_long"
          name={`${namespace}.gps_long`}
          readOnly={false}
        />
        <CustomText
          label="Sijoituspaikan lisäselite"
          name={`${namespace}.sijoituspaikan_lisaselite`}
          readOnly={false}
        />
        <CustomNumber
          label="Kerros"
          name={`${namespace}.kerros`}
          readOnly={false}
        />
        <CustomNumber
          label="Etaisyys radasta jos eri"
          name={`${namespace}.etaisyys_radasta_jos_eri`}
          readOnly={false}
        />
        <CustomText
          label="Malli"
          name={`${namespace}.malli`}
          readOnly={false}
        />
      </Form>
    </>
  )
}

export default AsennettuAnturiForm;