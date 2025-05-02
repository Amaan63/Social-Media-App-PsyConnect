import { Avatar, Button, CardHeader, IconButton } from "@mui/material";
import React from "react";
import { red } from "@mui/material/colors";

const PopularUserCard = () => {
  return (
    <div>
      <CardHeader
        avatar={
          <Avatar sx={{ bgcolor: red[500] }} aria-label="recipe">
            A
          </Avatar>
        }
        action={<Button size="small">Follow</Button>}
        title="Ahmed"
        subheader="@Ahmy"
      />
    </div>
  );
};

export default PopularUserCard;
