import React, { useState } from "react";
import { Card, Avatar, CardHeader } from "@mui/material";
import { grey } from "@mui/material/colors";
import ErrorIcon from "@mui/icons-material/Error";
import { useDispatch, useSelector } from "react-redux";
import { searchUserAction } from "../../Redux/Authentication/authentication.action";
import { SEARCH_USER_SUCCESS } from "../../Redux/Authentication/authentication.actionType";
import { createChatAction } from "../../Redux/Message/message.action";

const SearchUser = () => {
  const [username, setUsername] = useState("");
  const dispatch = useDispatch();
  const { auth } = useSelector((store) => store);

  const handleSearchUser = (e) => {
    const value = e.target.value;
    setUsername(value);

    if (value.trim() === "") {
      dispatch({ type: SEARCH_USER_SUCCESS, payload: [] }); // Clear search
    } else {
      dispatch(searchUserAction(value));
    }
  };

  const handleClick = (id) => {
    dispatch(createChatAction({ targetUser: id }));
  };

  return (
    <div className="py-5 relative">
      <input
        className="bg-transparent border border-[#3b4054] outline-none w-full px-5 py-3 rounded-full"
        type="text"
        name="search"
        placeholder="Search User......"
        onChange={handleSearchUser}
        value={username}
      />

      {/* Result container */}
      {username && (
        <div className="absolute w-full z-10 top-[4.5rem] bg-white max-h-60 overflow-y-auto rounded shadow">
          {auth.searchUser.length > 0 ? (
            auth.searchUser.map((item) => (
              <Card
                key={item.id}
                sx={{
                  border: "1px solid",
                  borderColor: grey[500],
                  marginBottom: "0.5rem",
                }}
                className="cursor-pointer"
              >
                <CardHeader
                  onClick={() => handleClick(item.id)}
                  avatar={
                    <Avatar src="https://e7.pngegg.com/pngimages/799/987/png-clipart-computer-icons-avatar-icon-design-avatar-heroes-computer-wallpaper-thumbnail.png" />
                  }
                  title={`${item.firstName} ${item.lastName}`}
                  subheader={`@${item.firstName}${item.lastName}`}
                />
              </Card>
            ))
          ) : (
            <div className="flex items-center gap-2 p-2 bg-white border border-gray-300 rounded">
              <ErrorIcon className="text-red-500" />
              <h3 className="text-base font-semibold">User Not Found</h3>
            </div>
          )}
        </div>
      )}
    </div>
  );
};

export default SearchUser;
