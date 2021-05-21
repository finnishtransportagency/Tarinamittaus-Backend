import React from 'react';
import { Formik, Form, Field, ErrorMessage } from 'formik';
import * as Yup from "yup";
import IMittaus from '../types/interfaces/mittaus.interface';
import { observer } from 'mobx-react';
import MittausStore from '../stores/MittausStore';
import { CustomText } from '../components/CustomText';
import { FormikCustomDatePicker } from '../components/CustomDatePicker';
import { CustomNumber } from '../components/CustomNumber';
import MittausForm from './MittausForm';
import Container from 'react-bootstrap/Container';


const MittausView = observer(({ mittaus }: { mittaus: MittausStore }) => {
  return (
    <Container>
      <h1>Tärinätietojen syöttölomake</h1>
      <MittausForm mittaus={mittaus} />
    </Container>
  )
})
export default MittausView;

