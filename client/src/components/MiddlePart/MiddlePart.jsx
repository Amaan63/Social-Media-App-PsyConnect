import { Avatar, Card, IconButton } from "@mui/material";
import React, { useEffect, useState } from "react";
import StoryCircle from "./StoryCircle";
import AddIcon from "@mui/icons-material/Add";
import ImageIcon from "@mui/icons-material/Image";
import VideocamIcon from "@mui/icons-material/Videocam";
import ArticleIcon from "@mui/icons-material/Article";
import PostCard from "../Post/PostCard";
import CreatePostModal from "../Post/CreatePostModal";
import { useDispatch, useSelector } from "react-redux";
import { getAllPostAction } from "../../Redux/Post/post.action";
import { grey } from "@mui/material/colors";

const story = [11, 12, 13, 45, 69, 1];
const MiddlePart = () => {
  const dispatch = useDispatch();
  const { posts, error, newComment } = useSelector((store) => store.post);
  const user = useSelector((store) => store.auth.user);
  console.log(error);

  const [openCreatePostModal, setOpenCreatePostModal] = useState(false);

  const handleCloseCreatePostModal = () => setOpenCreatePostModal(false);

  const handleOpenCreatePostModel = () => {
    setOpenCreatePostModal(true);
    console.log("Open Post Model");
  };

  useEffect(() => {
    dispatch(getAllPostAction());
  }, [posts?.newComment, posts?.post, newComment]);

  return (
    <div className="px-4 sm:px-8 md:px-20">
      <section className="flex items-center p-5 rounded-b-md overflow-x-auto">
        <div className="flex flex-col items-center mr-4 cursor-pointer flex-shrink-0">
          <Avatar sx={{ width: "5rem", height: "5rem" }}>
            <AddIcon sx={{ fontSize: "3rem" }} />
          </Avatar>
          <p>New</p>
        </div>
        {story.map((item) => (
          <StoryCircle key={item} />
        ))}
      </section>

      <Card
        className="p-5 m-5"
        sx={{ border: "1px solid", borderColor: grey[500] }}
      >
        <div className="flex justify-between items-center cursor-pointer">
          <Avatar />
          <input
            onClick={handleOpenCreatePostModel}
            readOnly
            className="outline-none w-full md:w-[90%] rounded-full px-5 bg-transparent border border-[#3b4054]"
            type="text"
            placeholder="What's on your mind?"
          />
        </div>
        <div
          className="flex justify-center space-x-5 mt-5"
          onClick={handleOpenCreatePostModel}
        >
          <div className="flex items-center">
            <IconButton color="primary">
              <ImageIcon />
            </IconButton>
            <span className="ml-2">Media</span>
          </div>

          <div className="flex items-center">
            <IconButton color="primary">
              <VideocamIcon />
            </IconButton>
            <span className="ml-2">Video</span>
          </div>

          <div className="flex items-center">
            <IconButton color="primary">
              <ArticleIcon />
            </IconButton>
            <span className="ml-2">Write Article</span>
          </div>
        </div>
      </Card>

      <div className="mt-5 m-8 space-y-5">
        {error ? (
          <div className="text-center text-red-500 py-10">
            Cannot connect to the server. Please try again later.
          </div>
        ) : !posts ? (
          <div className="text-center text-gray-500 py-10">
            Loading posts...
          </div>
        ) : posts.length === 0 ? (
          <div className="text-center text-red-500 py-10">
            No Posts Available.
          </div>
        ) : (
          posts.map((item) => <PostCard key={item.id} item={item} />)
        )}
      </div>

      <div>
        <CreatePostModal
          user={user}
          handleClose={handleCloseCreatePostModal}
          open={openCreatePostModal}
        />
      </div>
    </div>
  );
};

export default MiddlePart;
