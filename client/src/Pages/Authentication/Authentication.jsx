import React from "react";
import { Grid, Card } from "@mui/material";
import authBg from "../../assets/images/auth/authBg.jpg";
import Login from "./Login";
import Register from "./Register";
import { Route, Routes } from "react-router-dom";

const Authentication = () => {
  return (
    <Grid className="min-h-screen flex flex-col md:grid md:grid-cols-12 ">
      {/* Image Section */}
      <Grid className="md:col-span-8 h-60 md:h-auto mt-3">
        <img
          src={authBg}
          alt="Authentication background"
          className="h-full w-full object-cover"
        />
      </Grid>

      {/* Form Section */}
      <Grid className="md:col-span-4 flex items-center justify-center">
        <div className="px-6 md:px-10 w-full">
          <Card className="p-6 md:p-8">
            <div className="flex flex-col items-center mb-5 space-y-1">
              <h1 className="logo text-center text-2xl font-semibold animate-bounce">
                Psy Connect
              </h1>
              <p className="text-center text-sm w-full md:w-[70%]">
                Connecting Lives, Sharing Stories: Your Social World Your Way
              </p>
            </div>
            <Routes>
              <Route path="/" element={<Login />} />
              <Route path="/login" element={<Login />} />
              <Route path="/register" element={<Register />} />
            </Routes>
          </Card>
        </div>
      </Grid>
    </Grid>
  );
};

export default Authentication;
