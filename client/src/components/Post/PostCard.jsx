import React from "react";
import {
  Avatar,
  Card,
  CardActions,
  CardContent,
  CardHeader,
  CardMedia,
  IconButton,
  Typography,
} from "@mui/material";
import FavoriteIcon from "@mui/icons-material/Favorite";
import FavoriteBorderIcon from "@mui/icons-material/FavoriteBorder";
import ShareIcon from "@mui/icons-material/Share";
import { red } from "@mui/material/colors";
import MoreVertIcon from "@mui/icons-material/MoreVert";
import ChatBubbleIcon from "@mui/icons-material/ChatBubble";
import BookmarkIcon from "@mui/icons-material/Bookmark";
import BookmarkBorderIcon from "@mui/icons-material/BookmarkBorder";

const PostCard = ({ item }) => {
  console.log("Video URL:", item.video);
  const isValidImage = item.image && item.image !== "Not Provided";
  const isValidVideo = item.video && item.video !== "Not Provided";

  return (
    <Card sx={{ width: "100%" }} elevation={3}>
      <CardHeader
        avatar={
          <Avatar sx={{ bgcolor: red[500] }} aria-label="recipe">
            R
          </Avatar>
        }
        action={
          <IconButton aria-label="settings">
            <MoreVertIcon />
          </IconButton>
        }
        title={item.user.firstName + " " + item.user.lastName}
        subheader={
          "@" +
          item.user.firstName.toLowerCase() +
          " " +
          item.user.lastName.toLowerCase()
        }
      />
      {isValidImage ? (
        <CardMedia
          component="img"
          height="194"
          image={item.image}
          alt="Post image"
        />
      ) : isValidVideo ? (
        <CardMedia
          component="div"
          sx={{ position: "relative", paddingTop: "56.25%" }}
        >
          <video
            controls
            style={{
              position: "absolute",
              top: 0,
              left: 0,
              width: "100%",
              height: "100%",
            }}
          >
            <source src={item.video} type="video/mp4" />
            Your browser does not support the video tag.
          </video>
        </CardMedia>
      ) : null}

      <CardContent>
        <Typography variant="body2" sx={{ color: "text.secondary" }}>
          {item.caption}
        </Typography>
      </CardContent>
      <CardActions className="flex justify-between" disableSpacing>
        <div>
          <IconButton>
            {true ? <FavoriteIcon /> : <FavoriteBorderIcon />}
          </IconButton>
          <IconButton>
            <ShareIcon />
          </IconButton>
          <IconButton>
            <ChatBubbleIcon />
          </IconButton>
        </div>
        <div>{true ? <BookmarkIcon /> : <BookmarkBorderIcon />}</div>
      </CardActions>
    </Card>
  );
};

export default PostCard;
