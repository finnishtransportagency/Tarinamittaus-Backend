import { Formik } from "formik";
import { observer } from "mobx-react";
import * as Yup from 'yup';
import { CustomNumber } from "../components/CustomNumber";
import { CustomText } from "../components/CustomText";
import AsennettuAnturiStore from "../stores/AsennettuAnturiStore";
import IAsennettuAnturi from "../types/interfaces/asennettuAnturi.interface";

const validationSchema = Yup.object({
  malli: Yup.string().required('Malli vaaditaan'),
  gps_lat: Yup.number().min(-90).max(90).required('Koordinaatit eivät voi olla tyhjiä'),
  gps_long: Yup.number().min(0).max(180).required('Koordinaatit eivät voi olla tyhjiä'),
  etaisyys_radasta_jos_eri: Yup.number().positive().required(),
  kerros: Yup.number().integer().positive().required(),
  sijoituspaikan_lisaselite: Yup.string()
  // asennuspaikantyyppi:
  // anturikohtaisettunnusarvot:
})

const AsennettuAnturiForm = ({ asennettuAnturi }: { asennettuAnturi: AsennettuAnturiStore }) => {
  return (
    <>
    <h3>Anturien ja anturikohtaisten arvojen lisääminen</h3>
      {/* <Formik
        initialValues={{ ...asennettuAnturi }}
        validationSchema={validationSchema}
        onSubmit={(values, { setSubmitting }) => {
          setTimeout(() => {
            alert(JSON.stringify(values, null, 2));
            setSubmitting(false);
          }, 400);
        }}
      >
        {formik => */}
          {/* <form onSubmit={formik.handleSubmit}> */}
            <CustomNumber
              label="gps_lat"
              name="gps_lat"
              readOnly={false}
            />
            <CustomNumber
              label="gps_long"
              name="gps_long"
              readOnly={false}
            />
            <CustomText
              label="Sijoituspaikan lisäselite"
              name="sijoituspaikan_lisaselite"
              readOnly={false}
            />
            <CustomNumber
              label="Kerros"
              name="kerros"
              readOnly={false}
            />
            <CustomNumber
              label="Etaisyys radasta jos eri"
              name="etaisyys_radasta_jos_eri"
              readOnly={false}
            />
            <CustomText
              label="Malli"
              name="malli"
              readOnly={false}
            />
          {/* </form> */}
      {/* //   }
      // </Formik> */}

    </>
  )
}

export default AsennettuAnturiForm;