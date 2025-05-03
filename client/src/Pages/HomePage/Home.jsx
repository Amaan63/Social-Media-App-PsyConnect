import React, { useEffect } from "react";
import { Grid } from "@mui/material";
import { Route, Routes, useLocation } from "react-router-dom";
import Reels from "../../components/Reels/Reels";
import CreateReelsForm from "../../components/Reels/CreateReelsForm";
import Profile from "../Profile/Profile";
import HomeRight from "../../components/Home Components/HomeRight";
import SideBar from "../../components/SideBar/SideBar";
import MiddlePart from "../../components/MiddlePart/MiddlePart";
import { useDispatch, useSelector } from "react-redux";
import { getUserProfileAction } from "../../Redux/Authentication/authentication.action";

const Home = () => {
  const dispatch = useDispatch();
  const location = useLocation();
  const jwt = localStorage.getItem("jwt");
  const { auth } = useSelector((store) => store);

 
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
          lg={location.pathname === "/" ? 6 : 9}
          className="px-5 flex justify-center"
        >
          <Routes>
            <Route path="/" element={<MiddlePart />} />
            <Route path="/reels" element={<Reels />} />
            <Route path="/create-reels" element={<CreateReelsForm />} />
            <Route path="/profile/:id" element={<Profile />} />
          </Routes>
        </Grid>

        {location.pathname === "/" && (
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
