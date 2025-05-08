import React from "react";

const ChatMessage = () => {
  return (
    <div>
      <div
        className={`p-1 ${
          true ? "rounded-md" : "px-5 rounded-full"
        } bg-[#191c29]`}
      >
        <p className="">Message</p>
      </div>
    </div>
  );
};

export default ChatMessage;
