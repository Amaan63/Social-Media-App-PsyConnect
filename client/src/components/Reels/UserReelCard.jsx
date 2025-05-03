import React from "react";

const UserReelCard = () => {
  return (
    <div className="w-[25rem] h-[30rem] p-2 flex items-center justify-center border-2 border-black rounded-lg shadow-lg">
      <video
        controls
        className="max-w-full max-h-full object-contain"
        src="https://cdn.pixabay.com/video/2025/01/03/250395_tiny.mp4"
      />
    </div>
  );
};

export default UserReelCard;
