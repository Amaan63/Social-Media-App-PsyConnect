import React from "react";
import { Card } from "@mui/material";
import { Avatar, CardHeader } from "@mui/material";
import { grey } from "@mui/material/colors";
import ErrorIcon from "@mui/icons-material/Error";
import { Box } from "@mui/system";

const SearchUser = () => {
  const handleSearchUser = () => {
    console.log("Searched User:- ");
  };
  const handleClick = (id) => {
    console.log(id);
  };
  return (
    <div>
      <div className="py-5 relative">
        <input
          className="bg-transparent border border-[#3b4054] outline-none w-full px-5 py-3 rounded-full"
          type="text"
          name="search"
          id=""
          placeholder="Search User......"
          onChange={handleSearchUser}
        />
      </div>
      {false ? (
        <Card sx={{ border: "1px solid", borderColor: grey[500] }}>
          <CardHeader
            onClick={() => {
              handleClick(2);
            }}
            avatar={
              <Avatar src="https://e7.pngegg.com/pngimages/799/987/png-clipart-computer-icons-avatar-icon-design-avatar-heroes-computer-wallpaper-thumbnail.png" />
            }
            title="Amaan Sayyed"
            subheader={"amaansayyed63"}
          />
        </Card>
      ) : (
        <div className="flex justify-center-safe gap-2  p-2 rounded">
          <ErrorIcon className="text-red-500" />
          <h3 className="text-base font-semibold">User Not Found</h3>
        </div>
      )}
    </div>
  );
};

export default SearchUser;
