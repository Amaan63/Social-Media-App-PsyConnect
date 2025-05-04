import React, { useEffect, useState } from "react";
import { Formik, Form, Field, ErrorMessage } from "formik";
import {
  Button,
  FormControlLabel,
  Radio,
  RadioGroup,
  TextField,
} from "@mui/material";
import * as Yup from "yup";
import { useDispatch, useSelector } from "react-redux";
import { registerUserAction } from "../../Redux/Authentication/authentication.action";
import { useNavigate } from "react-router-dom";
import { toast } from "react-toastify";

const initialValues = {
  firstName: "",
  lastName: "",
  email: "",
  password: "",
  gender: "",
};

const validationSchema = Yup.object({
  firstName: Yup.string().required("First Name is Required"),
  lastName: Yup.string().required("Last Name is Required"),
  email: Yup.string().email("Invalid Email").required("Email is Required"),
  password: Yup.string()
    .min(6, "Password Must be at least 6 Characters")
    .required("Password is Required"),
  gender: Yup.string().required("Gender is Required"),
});

const Register = () => {
  const [formValue, setFormValue] = useState();
  const [submitted, setSubmitted] = useState(false); // ✅ Track form submission
  const dispatch = useDispatch();
  const navigate = useNavigate();
  const auth = useSelector((state) => state.auth);

  const handleSubmit = (values) => {
    console.log("Form submitted", values);
    setFormValue(values);
    dispatch(registerUserAction({ data: values }));
    setSubmitted(true);
  };
  useEffect(() => {
    if (submitted) {
      if (auth.jwt) {
        toast.success("Registration successful!");
        navigate("/");
      }

      if (auth.error) {
        toast.error("Registration failed. Please try again.");
        setSubmitted(false); // Reset submitted flag for retry
      }
    }
  }, [auth.jwt, auth.error, submitted]);

  return (
    <>
      <Formik
        initialValues={initialValues}
        validationSchema={validationSchema}
        onSubmit={handleSubmit}
      >
        {({ values, handleChange }) => (
          <Form className="space-y-5">
            <div>
              <Field
                as={TextField}
                name="firstName"
                placeholder="Enter First Name"
                variant="outlined"
                fullWidth
              />
              <ErrorMessage
                name="firstName"
                component="div"
                className="text-red-500"
              />
            </div>

            <div>
              <Field
                as={TextField}
                name="lastName"
                placeholder="Enter Last Name"
                variant="outlined"
                fullWidth
              />
              <ErrorMessage
                name="lastName"
                component="div"
                className="text-red-500"
              />
            </div>

            <div>
              <Field
                as={TextField}
                name="email"
                placeholder="Enter Email"
                type="email"
                variant="outlined"
                fullWidth
              />
              <ErrorMessage
                name="email"
                component="div"
                className="text-red-500"
              />
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

            <div>
              Gender
              <RadioGroup
                row
                name="gender"
                value={values.gender}
                onChange={handleChange}
              >
                <FormControlLabel
                  value="female"
                  control={<Radio />}
                  label="Female"
                />
                <FormControlLabel
                  value="male"
                  control={<Radio />}
                  label="Male"
                />
                <FormControlLabel
                  value="other"
                  control={<Radio />}
                  label="Other"
                />
              </RadioGroup>
              <ErrorMessage
                name="gender"
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
              Regsiter
            </Button>
          </Form>
        )}
      </Formik>
      <div className="flex gap-2 items-center justify-center pt-5">
        <p>Already have an Account ?</p>
        <Button
          variant="contained"
          color="secondary"
          onClick={() => navigate("/")}
        >
          Login
        </Button>
      </div>
    </>
  );
};

export default Register;
