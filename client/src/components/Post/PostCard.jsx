import React, { useState } from "react";
import {
  Avatar,
  Card,
  CardActions,
  CardContent,
  CardHeader,
  CardMedia,
  Divider,
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
  const isValidImage = item.image && item.image !== "Not Provided";
  const isValidVideo = item.video && item.video !== "Not Provided";
  const [showComments, setShowComments] = useState(false);

  const handleShowComment = () => setShowComments(!showComments);

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
          <IconButton onClick={handleShowComment}>
            <ChatBubbleIcon />
          </IconButton>
        </div>
        <div>{true ? <BookmarkIcon /> : <BookmarkBorderIcon />}</div>
      </CardActions>
      {showComments && (
        <section>
          <div className="flex items-center space-x-5 mx-3 my-5">
            <Avatar sx={{}} />
            <input
              onKeyPress={(e) => {
                if (e.key == "Enter") {
                  console.log("Enter Pressed ------ ", e.target.value);
                  {
                    ("}");
                  }
                }
              }}
              className="w-full outline-none bg-transparent border border-[#3b4054] rounded-full px-5 py-2"
              type="text"
              name="comment"
              id=""
              placeholder="write your comments....."
            />
          </div>
          <Divider />
          <div className="mx-3 space-y-2 my-5 text-xs">
            <div className="flex justify-between items-center">
              <div className="flex items-center space-x-5">
                <Avatar
                  sx={{ height: "2rem", width: "2rem", fontSize: "0.8rem" }}
                >
                  A
                </Avatar>
                <p>Nice Image</p>
              </div>
            </div>
          </div>
        </section>
      )}
    </Card>
  );
};

export default PostCard;
