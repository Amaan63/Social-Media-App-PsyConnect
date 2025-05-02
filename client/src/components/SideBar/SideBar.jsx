import React from "react";
import { navigationMenu } from "./SideBarNavigation";
import { Avatar, Button, Card, Divider, Menu, MenuItem } from "@mui/material";
import avatarIcon from "../../assets/images/Home/Avatar.svg";
import MoreHorizIcon from "@mui/icons-material/MoreHoriz";
const SideBar = () => {
  const [anchorEl, setAnchorEl] = React.useState(null);
  const open = Boolean(anchorEl);
  const handleClick = (event) => {
    setAnchorEl(event.currentTarget);
  };
  const handleClose = () => {
    setAnchorEl(null);
  };
  return (
    <Card className="card h-screen flex flex-col justify-between py-5">
      <div className="space-y-8 pl-5">
        <div className="">
          <span className="logo font-bold text-2xl">PsyConnect</span>
        </div>
        <div className="space-y-8">
          {navigationMenu.map((item) => (
            <div
              key={item.title}
              className=" cursor-pointer flex space-x-3 items-center"
            >
              {item.icon}
              <p className="text-xl ">{item.title}</p>
            </div>
          ))}
        </div>
        <div>
          <Divider />
          <div className="pl-5 flex items-center justify-between mt-4">
            <div className="flex items-center space-x-3">
              <Avatar src={avatarIcon} />
              <div>
                <p className="font-bold">Amaan Sayyed</p>
                <p className="opacity-70">@Amaan63</p>
              </div>
            </div>
            <Button
              id="basic-button"
              aria-controls={open ? "basic-menu" : undefined}
              aria-haspopup="true"
              aria-expanded={open ? "true" : undefined}
              onClick={handleClick}
            >
              <MoreHorizIcon />
            </Button>
            <Menu
              id="basic-menu"
              anchorEl={anchorEl}
              open={open}
              onClose={handleClose}
              MenuListProps={{
                "aria-labelledby": "basic-button",
              }}
            >
              <MenuItem onClick={handleClose}>Profile</MenuItem>
              <MenuItem onClick={handleClose}>My account</MenuItem>
              <MenuItem onClick={handleClose}>Logout</MenuItem>
            </Menu>
          </div>
        </div>
      </div>
    </Card>
  );
};

export default SideBar;
