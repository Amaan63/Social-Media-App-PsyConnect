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
import { useDispatch, useSelector } from "react-redux";
import {
  createCommentAction,
  likeCommentAction,
  likePostAction,
} from "../../Redux/Post/post.action";
import { isLikedByReqUser } from "../../utils/isLikedByReqUser";
import { ErrorMessage, Field, Form, Formik } from "formik";
import * as Yup from "yup";
import SendIcon from "@mui/icons-material/Send";

const PostCard = ({ item }) => {
  const dispatch = useDispatch();

  const user = useSelector((store) => store.auth.user);
  const isValidImage = item.image && item.image !== "Not Provided";
  const isValidVideo = item.video && item.video !== "Not Provided";
  const [showComments, setShowComments] = useState(false);
  const handleCreateComment = (content) => {
    const reqData = {
      postId: item.id,
      data: {
        content,
      },
    };
    dispatch(createCommentAction(reqData));
  };

  const handleShowComment = () => setShowComments(!showComments);

  const handleLikePost = () => {
    dispatch(likePostAction(item.id));
  };

  const handleLikeComment = (commentId) => {
    dispatch(likeCommentAction(commentId));
  };

  // console.log("is Liked ..", isLikedByReqUser(user.id, item));

  return (
    <Card sx={{ width: "100%" }} elevation={3}>
      <CardHeader
        avatar={
          <Avatar sx={{ bgcolor: red[500] }} aria-label="recipe">
            {item.user.firstName[0]}
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
        // {/*<CardMedia
        //   component="img"
        //   height="100"
        //   image={item.image}
        //   alt="Post image"
        // /> */}
        <div className="flex justify-center items-center h-full">
          <img className="w-auto h-[30rem]" src={item.image} alt="" />
        </div>
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
          <IconButton onClick={handleLikePost}>
            {isLikedByReqUser(user.id, item) ? (
              <FavoriteIcon className="text-red-500 " />
            ) : (
              <FavoriteBorderIcon />
            )}
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
          <Formik
            initialValues={{
              comment: "",
            }}
            validationSchema={Yup.object({
              comment: Yup.string().trim().required("Comment cannot be empty"),
            })}
            onSubmit={(values, { resetForm }) => {
              handleCreateComment(values.comment);
              resetForm(); // Clear input after submission
            }}
          >
            {({ handleReset }) => (
              <Form>
                <div className="flex items-center space-x-3 mx-3 my-5">
                  <Avatar sx={{}} />
                  <Field
                    type="text"
                    name="comment"
                    placeholder="Write your comment..."
                    className="w-full outline-none bg-transparent border border-[#3b4054] rounded-full px-5 py-2"
                  />
                  <button type="submit" className="text-[#3b4054]">
                    <SendIcon />
                  </button>
                </div>
                <ErrorMessage
                  name="comment"
                  component="div"
                  className="text-red-500 text-xs mx-3 -mt-2"
                />
              </Form>
            )}
          </Formik>

          <Divider />
          {item.comments?.map((comment) => (
            <div className="mx-3 space-y-2 my-5 text-xs flex items-center justify-between">
              <div key={comment.id} className="flex items-center space-x-5">
                <Avatar
                  sx={{ height: "2rem", width: "2rem", fontSize: "0.8rem" }}
                >
                  {comment.user.firstName[0]}
                </Avatar>
                <p>{comment.content}</p>
              </div>
              <div>
                <IconButton onClick={() => handleLikeComment(comment.id)}>
                  {isLikedByReqUser(user.id, comment) ? (
                    <FavoriteIcon className="text-red-500" fontSize="inherit" />
                  ) : (
                    <FavoriteBorderIcon />
                  )}
                </IconButton>
              </div>
            </div>
          ))}
        </section>
      )}
    </Card>
  );
};

export default PostCard;
