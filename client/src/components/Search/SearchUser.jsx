import React, { useState } from "react";
import { Card, Avatar, CardHeader } from "@mui/material";
import { grey } from "@mui/material/colors";
import ErrorIcon from "@mui/icons-material/Error";

const SearchUser = () => {
  const [username, setUsername] = useState("");
  const handleSearchUser = (e) => {
    setUsername(e.target.value);
    console.log("Searched User:- ");
  };
  const handleClick = (id) => {
    console.log(id);
    setUsername("");
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
        {username ? (
          <Card
            sx={{ border: "1px solid", borderColor: grey[500] }}
            className="absolute w-full z-10 top-[4.5rem] cursor-pointer"
          >
            <CardHeader
              onClick={() => {
                handleClick(2);
                setUsername("");
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
    </div>
  );
};

export default SearchUser;
