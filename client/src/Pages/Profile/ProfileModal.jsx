import * as React from "react";
import Box from "@mui/material/Box";
import Button from "@mui/material/Button";
import Modal from "@mui/material/Modal";
import { useDispatch, useSelector } from "react-redux";
import { useFormik } from "formik";
import { Avatar, IconButton, TextField } from "@mui/material";
import CloseIcon from "@mui/icons-material/Close";
import { updateUserProfileAction } from "../../Redux/Authentication/authentication.action";
import { toast } from "react-toastify";
import * as Yup from "yup"; // Importing Yup for validation

// Validation Schema using Yup
const validationSchema = Yup.object({
  firstName: Yup.string().required("First name is required"),
  lastName: Yup.string().required("Last name is required"),
});

const style = {
  position: "absolute",
  top: "50%",
  left: "50%",
  transform: "translate(-50%, -50%)",
  width: 600,
  bgcolor: "background.paper",
  border: "2px solid #000",
  boxShadow: 24,
  p: 2,
  outline: "none",
  overflowY: "none", // ✅ correct key
  borderRadius: 3,
};

export default function ProfileModal({ open, handleClose }) {
  //console.log(open);
  const dispatch = useDispatch();
  const user = useSelector((state) => state.auth.user);

  const formik = useFormik({
    enableReinitialize: true, // ✅ allow form to reinitialize with updated values
    initialValues: {
      firstName: user?.firstName || "Loading",
      lastName: user?.lastName || "Loading",
    },
    validationSchema, // Pass validation schema to Formik
    onSubmit: async (values) => {
      try {
        // Dispatching the update action
        await dispatch(updateUserProfileAction({ ...user, ...values })); // included the Id and Required Field

        // If the server is down, it'll throw an error and we'll catch it here
        toast.success("Profile updated successfully!");
        handleClose(); // Close the modal on success
      } catch (error) {
        // Check if the error is related to network issues or backend server being down
        if (error.response) {
          // If backend returned an error
          toast.error("Failed to update profile. Please try again.");
        } else if (error.request) {
          // If request was made but no response received (likely server is down)
          toast.error(
            "Unable to reach the server. Please check your internet connection or try again later."
          );
        } else {
          // Other errors (e.g., request configuration issues)
          toast.error("An unknown error occurred. Please try again.");
        }
      }
    },
  });

  return (
    <div>
      <Modal
        open={open}
        onClose={handleClose}
        aria-labelledby="modal-modal-title"
        aria-describedby="modal-modal-description"
      >
        <Box sx={style}>
          <form onSubmit={formik.handleSubmit}>
            <div className="flex items-center justify-between">
              <div className="flex items-center space-x-3">
                <IconButton onClick={handleClose}>
                  <CloseIcon className="text-red-500" />
                </IconButton>
                <p>Edit Profile</p>
              </div>
              <Button type="submit">Save</Button>
            </div>
            <div>
              <div className="h-[15rem]">
                <img
                  className="w-full h-full rounded-t-md"
                  src="https://cdn.pixabay.com/photo/2018/07/04/11/58/xiamen-3515964_1280.jpg"
                  alt=""
                />
              </div>
              <div className="pl-5">
                <Avatar
                  className="transform -translate-y-24"
                  sx={{ width: "10rem", height: "10rem" }}
                  src="https://cdn.pixabay.com/photo/2016/01/25/19/48/man-1161337_1280.jpg"
                />
              </div>
            </div>
            <div className="flex flex-col gap-4 mt-4 px-4">
              <TextField
                fullWidth
                id="firstName"
                name="firstName"
                label="First Name"
                value={formik.values.firstName}
                onChange={formik.handleChange}
                sx={{ width: "100%" }}
                error={
                  formik.touched.firstName && Boolean(formik.errors.firstName)
                }
                helperText={formik.touched.firstName && formik.errors.firstName}
              />
              <TextField
                fullWidth
                id="lastName"
                name="lastName"
                label="last Name"
                value={formik.values.lastName}
                onChange={formik.handleChange}
                sx={{ width: "100%" }}
                error={
                  formik.touched.lastName && Boolean(formik.errors.lastName)
                }
                helperText={formik.touched.lastName && formik.errors.lastName}
              />
            </div>
          </form>
        </Box>
      </Modal>
    </div>
  );
}
