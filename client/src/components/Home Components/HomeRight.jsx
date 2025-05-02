import React from "react";
import SearchUser from "../Search/SearchUser";
import PopularUserCard from "./PopularUserCard";
import { Card } from "@mui/material";

const popularUser = [1, 2, 3, 4, 5, 6];
const HomeRight = () => {
  return (
    <div className=" ">
      <SearchUser />
      <Card className=" m-5">
        <div className="flex justify-between p-5  items-center">
          <p className="font-semibold backdrop-opacity-70 ">
            Suggestion for you
          </p>
          <p className="text-xs font-semibold  opacity-95">View All</p>
        </div>
        <div>
          {popularUser.map((item) => (
            <PopularUserCard key={item} />
          ))}
        </div>
      </Card>
    </div>
  );
};

export default HomeRight;
