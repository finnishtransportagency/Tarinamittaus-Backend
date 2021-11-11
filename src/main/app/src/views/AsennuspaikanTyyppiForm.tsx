import React from "react";

import { Field } from "formik";
import AsennuspaikanTyyppiStore from "../stores/AsennuspaikanTyyppiStore";
import SeliteTypeEnum from "../types/enums/seliteType.enum";
import { CustomText } from "../components/CustomText";
import { Form, Col, Row } from "react-bootstrap";

const AsennuspaikanTyyppiForm = ({
  paikka,
  namespace,
}: {
  paikka: AsennuspaikanTyyppiStore;
  namespace: string;
}) => {
  return (
    <div key={namespace}>
      <Form.Group as={Row}>
        <Form.Label column sm="4">
          Anturin sijoituspaikka
        </Form.Label>
        <Col sm="2">
          <Field name={`${namespace}.selite`} as="select">
            {Object.keys(SeliteTypeEnum).map((key) => (
              <option value={key} key={key}>
                {key}
              </option>
            ))}
          </Field>
        </Col>
        <Col sm="6">
          <CustomText
            label="Anturin sijoituspaikan lisätiedot"
            name={`${namespace}.lisatiedot`}
            readOnly={false}
          />
        </Col>
      </Form.Group>
    </div>
  );
};

export default AsennuspaikanTyyppiForm;
