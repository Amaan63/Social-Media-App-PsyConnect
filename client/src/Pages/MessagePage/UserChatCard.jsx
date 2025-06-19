import React from "react";
import { Avatar, Card, CardHeader, IconButton } from "@mui/material";
import MoreHorizIcon from "@mui/icons-material/MoreHoriz";
import { grey } from "@mui/material/colors";
import { useSelector } from "react-redux";

const UserChatCard = ({ chat }) => {
  const { auth, message } = useSelector((store) => store);
  return (
    <Card sx={{ border: "1px solid", borderColor: grey[500] }}>
      <CardHeader
        avatar={
          <Avatar
            sx={{
              width: "3.5rem",
              height: "3.5rem",
              fontSize: "1.5rem",
              bgcolor: "#191c29",
              color: "rgb(88,199,250)",
            }}
            src="https://e7.pngegg.com/pngimages/799/987/png-clipart-computer-icons-avatar-icon-design-avatar-heroes-computer-wallpaper-thumbnail.png"
          />
        }
        action={
          <IconButton>
            <MoreHorizIcon />
          </IconButton>
        }
        title={
          auth.user.id == chat.users[0].id
            ? chat.users[1].firstName + " " + chat.users[1].lastName
            : chat.users[0].firstName + " " + chat.users[0].lastName
        }
        subheader={"New Message....."}
      ></CardHeader>
    </Card>
  );
};

export default UserChatCard;
