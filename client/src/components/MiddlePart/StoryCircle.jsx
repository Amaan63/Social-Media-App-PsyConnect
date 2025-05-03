import React from "react";
import { Avatar } from "@mui/material";

const StoryCircle = () => {
  return (
    <div className="flex flex-col items-center cursor-pointer mr-2 sm:mr-4">
      <Avatar
        sx={{
          width: { xs: "3.5rem", sm: "4rem", md: "5rem" },
          height: { xs: "3.5rem", sm: "4rem", md: "5rem" },
        }}
        src="https://img.freepik.com/free-vector/businessman-character-avatar-isolated_24877-60111.jpg"
      />
      <p className="text-sm sm:text-base mt-1">New</p>
    </div>
  );
};

export default StoryCircle;
