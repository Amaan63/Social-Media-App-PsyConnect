import React, { useState } from "react";
import { useDispatch } from "react-redux";
import { Formik, Form, Field, ErrorMessage } from "formik";
import { Button, TextField } from "@mui/material";
import * as Yup from "yup";
import { loginUserAction } from "../../Redux/Authentication/authentication.action";

const initialValues = {
  email: "",
  password: "",
};

const validationSchema = Yup.object({
  email: Yup.string().email("Invalid Email").required("Email is Required"),
  password: Yup.string()
    .min(6, "Password Must be atleast 6 Characters")
    .required("Password is Required"),
});

const Login = () => {
  const [formValue, setFormValue] = useState();
  const dispatch = useDispatch();

  const handleSubmit = (values) => {
    console.log("Form submitted", values);
    dispatch(loginUserAction({ data: values }));
  };

  return (
    <Formik
      initialValues={initialValues}
      validationSchema={validationSchema}
      onSubmit={handleSubmit}
    >
      <Form className="space-y-5">
        <div>
          <Field
            as={TextField}
            name="email"
            placeholder="Enter Email"
            type="email"
            variant="outlined"
            fullWidth
          />
          <ErrorMessage name="email" component="div" className="text-red-500" />
        </div>

        <div>
          <Field
            as={TextField}
            name="password"
            placeholder="Enter Password"
            type="password"
            variant="outlined"
            fullWidth
          />
          <ErrorMessage
            name="password"
            component="div"
            className="text-red-500"
          />
        </div>

        <Button
          sx={{ padding: ".8rem 0rem" }}
          fullWidth
          type="submit"
          variant="contained"
          color="primary"
        >
          Login
        </Button>
      </Form>
    </Formik>
  );
};

export default Login;
