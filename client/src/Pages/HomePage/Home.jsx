import React from "react";
import { Grid } from "@mui/material";
import { Route, Routes, useLocation } from "react-router-dom";
import Reels from "../../components/Reels/Reels";
import CreateReelsForm from "../../components/Reels/CreateReelsForm";
import Profile from "../Profile/Profile";
import HomeRight from "../../components/Home Components/HomeRight";
import SideBar from "../../components/SideBar/SideBar";
import MiddlePart from "../../components/MiddlePart/MiddlePart";
import { useDispatch } from "react-redux";

const Home = () => {
  const dispatch = useDispatch();
  const location = useLocation();

  return (
    <div className="px-20">
      <Grid container spacing={0}>
        <Grid item xs={0} lg={3} sx={{ display: { xs: "none", lg: "block" } }}>
          <div className="sticky top-0">
            <SideBar />
          </div>
        </Grid>

        <Grid
          item
          xs={12}
          lg={location.pathname === "/home" ? 6 : 9}
          className="px-5 flex justify-center"
        >
          <Routes>
            <Route path="/" element={<MiddlePart />} />
            <Route path="profile/:id" element={<Profile />} />
            <Route path="reels" element={<Reels />} />
            <Route path="create-reels" element={<CreateReelsForm />} />
          </Routes>
        </Grid>

        {location.pathname === "/home" && (
          <Grid
            item
            xs={0}
            lg={3}
            sx={{ display: { xs: "none", lg: "block" } }}
            className="relative"
          >
            <div className="sticky top-0 w-full">
              <HomeRight />
            </div>
          </Grid>
        )}
      </Grid>
    </div>
  );
};

export default Home;
