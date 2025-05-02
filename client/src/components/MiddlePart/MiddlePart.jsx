import { Avatar, Card, IconButton } from "@mui/material";
import React from "react";
import StoryCircle from "./StoryCircle";
import AddIcon from "@mui/icons-material/Add";
import ImageIcon from "@mui/icons-material/Image";
import VideocamIcon from "@mui/icons-material/Videocam";
import ArticleIcon from "@mui/icons-material/Article";
import PostCard from "../Post/PostCard";

const story = [11, 12, 13, 45, 69, 1];
const posts = [1, 2, 3, 4, 5, 6];
const MiddlePart = () => {
  const handleOpenCreatePostModel = () => {
    console.log("Open Post Model");
  };
  return (
    <div className="px-20">
      <section className="flex  items-center p-5 rounded-b-md">
        <div className="flex flex-col items-center mr-4 cursor-pointer">
          <Avatar sx={{ width: "5rem", height: "5rem" }}>
            <AddIcon sx={{ fontSize: "3rem" }} />
          </Avatar>
          <p>New</p>
        </div>
        {story.map((item) => (
          <StoryCircle key={item} />
        ))}
      </section>

      <Card className="p-5 m-5 ">
        <div className="flex justify-between">
          <Avatar />
          <input
            readOnly
            className="outline-none w-[90%]  rounded-full px-5 bg-transparent border-[#3b4054] border"
            type="text"
          />
        </div>
        <div className="flex justify-center space-x-9 mt-5">
          <div className="flex items-center">
            <IconButton color="primary" onClick={handleOpenCreatePostModel}>
              <ImageIcon />
            </IconButton>
            <span className="ml-2">Media</span>
          </div>

          <div className="flex items-center">
            <IconButton color="primary" onClick={handleOpenCreatePostModel}>
              <VideocamIcon />
            </IconButton>
            <span className="ml-2">Video</span>
          </div>

          <div className="flex items-center">
            <IconButton color="primary" onClick={handleOpenCreatePostModel}>
              <ArticleIcon />
            </IconButton>
            <span className="ml-2">Write Article</span>
          </div>
        </div>
      </Card>
      <div className="mt-5 m-8  space-y-5">
        {posts.map((item) => (
          <PostCard key={item} />
        ))}
      </div>
    </div>
  );
};

export default MiddlePart;
