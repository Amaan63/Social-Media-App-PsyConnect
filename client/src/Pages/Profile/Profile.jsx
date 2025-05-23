import React, { useEffect } from "react";
import { useParams } from "react-router-dom";
import { Avatar, Box, Button, Card, Tab, Tabs } from "@mui/material";
import EditIcon from "@mui/icons-material/Edit";
import PersonAddIcon from "@mui/icons-material/PersonAdd";
import PostCard from "../../components/Post/PostCard";
import UserReelCard from "../../components/Reels/UserReelCard";
import { useDispatch, useSelector } from "react-redux";
import ProfileModal from "./ProfileModal";
import { getUsersPostAction } from "../../Redux/Post/post.action";

const tabs = [
  { value: "post", name: "Posts" },
  { value: "reels", name: "Reels" },
  { value: "saved", name: "Saved Post" },
  { value: "repost", name: "Repost" },
];
const posts = [1, 2, 3, 4, 5];
const reels = [1, 2, 3, 4, 5];
const savedPosts = [1, 2, 3, 4];
const Profile = () => {
  const user = useSelector((store) => store.auth.user);
  const { post } = useSelector((store) => store);
  const dispatch = useDispatch();
  const [value, setValue] = React.useState("post");
  const [open, setOpen] = React.useState(false);
  const handleOpenProfileModal = () => {
    //console.log("Opening modal..."); // Check this in browser console
    setOpen(true);
  };

  const handleClose = () => setOpen(false);

  const handleChange = (event, newValue) => {
    setValue(newValue);
  };

  useEffect(() => {
    dispatch(getUsersPostAction(user.id));
  }, [post.newComment, post.post]);

  return (
    <Card className="my-10 w-[70%] ">
      <div className="rounded-md">
        <div className="h-[15rem]">
          <img
            className="w-full h-full rounded-t-md"
            src="https://cdn.pixabay.com/photo/2018/07/04/11/58/xiamen-3515964_1280.jpg"
            alt=""
          />
        </div>
        <div className="px-5 flex justify-between items-center mt-5 h-[5rem]">
          <Avatar
            className="transform -translate-y-24"
            sx={{ width: "10rem", height: "10rem" }}
            src="https://cdn.pixabay.com/photo/2016/01/25/19/48/man-1161337_1280.jpg"
          />
          {true ? (
            <Button
              variant="outlined"
              color="primary"
              size="large"
              onClick={handleOpenProfileModal}
              sx={{
                minWidth: "fit-content",
                paddingX: 2,
                paddingY: 0.5,
                textTransform: "none",
                borderRadius: "20px",
                "&:hover": {
                  backgroundColor: "#0d6efd",
                  color: "white",
                },
              }}
            >
              <EditIcon />
              Edit Profile
            </Button>
          ) : (
            <Button
              variant="contained"
              size="large"
              color="primary"
              sx={{
                minWidth: "fit-content",
                paddingX: 2,
                paddingY: 0.5,
                textTransform: "none",
                borderRadius: "20px",
                "&:hover": {
                  backgroundColor: "#0d6efd",
                  color: "white",
                },
              }}
            >
              <PersonAddIcon />
              Follow
            </Button>
          )}
        </div>
        <div className="p-5">
          <div>
            {user ? (
              <>
                <h1 className="py-1 font-bold text-xl">
                  {`${user.firstName} ${user.lastName}`}
                </h1>
                <p>
                  @
                  {`${user.firstName.toLowerCase()}_${user.lastName.toLowerCase()}`}
                </p>
              </>
            ) : (
              <p>Loading...</p>
            )}
          </div>
          <div className="flex gap-5 items-center py-3">
            <span>41 post</span>
            <span>35 Followers</span>
            <span>5 Followings</span>
          </div>
          <div>
            <p>
              Lorem ipsum dolor sit amet consectetur adipisicing elit.
              Repellendus iure quasi, sint possimus excepturi distinctio
              voluptas facere quia, delectus eveniet neque sunt optio? Officiis,
              earum eaque at tempora distinctio rerum?
            </p>
          </div>
        </div>
        <section>
          <Box sx={{ width: "100%", borderBottom: 3, borderColor: "divider" }}>
            <Tabs
              value={value}
              onChange={handleChange}
              textColor="dark"
              indicatorColor="primary"
              aria-label="secondary tabs example"
            >
              {tabs.map((item) => (
                <Tab key={item.value} value={item.value} label={item.name} />
              ))}
            </Tabs>
          </Box>
          <div className="flex justify-center m-5">
            {value === "post" ? (
              <div className="space-y-5 w-[100%] ">
                {post.posts.map((item) => (
                  <div key={item} className="w-full">
                    <PostCard item={item} />
                  </div>
                ))}
              </div>
            ) : value === "reels" ? (
              <div className="flex justify-center flex-wrap gap-3 my-2 ">
                {reels.map((item) => (
                  <UserReelCard key={item} />
                ))}
              </div>
            ) : value === "saved" ? (
              <div className="space-y-5 w-[100%] ">
                {savedPosts.map((item) => (
                  <div key={item} className="w-full">
                    <PostCard />
                  </div>
                ))}
              </div>
            ) : (
              <div>Repost</div>
            )}
          </div>
        </section>
      </div>
      <section>
        <ProfileModal open={open} handleClose={handleClose} />
      </section>
    </Card>
  );
};

export default Profile;
