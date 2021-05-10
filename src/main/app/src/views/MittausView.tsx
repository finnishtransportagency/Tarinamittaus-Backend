// Render Prop
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


const MittausView = observer(({ mittaus }: { mittaus: MittausStore }) => {
  return (
    <>
      <h1>Lisää uusi mittaus</h1>
      <MittausForm mittaus={mittaus} />
    </>
  )
})
export default MittausView;

