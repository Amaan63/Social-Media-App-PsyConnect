import React from "react";
import { Avatar } from "@mui/material";

const StoryCircle = () => {
  return (
    <div className="flex flex-col items-center mr-4 cursor-pointer">
      <Avatar
        sx={{ width: "5rem", height: "5rem" }}
        src="https://img.freepik.com/free-vector/businessman-character-avatar-isolated_24877-60111.jpg"
      ></Avatar>
      <p>New</p>
    </div>
  );
};

export default StoryCircle;
