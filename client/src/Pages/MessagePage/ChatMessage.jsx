import React from "react";

const ChatMessage = () => {
  return (
    <div
      className={`flex ${true ? "justify-start" : "justify-end"} text-white`}
    >
      <div
        className={`p-1 ${
          true ? "rounded-md" : "px-5 rounded-full"
        } bg-[#191c29] `}
      >
        {true && (
          <img
            className="w-[12rem] h-[17rem] object-cover rounded-md"
            alt=""
            src="https://cdn.pixabay.com/photo/2014/06/29/13/08/gosling-379393_1280.jpg"
          />
        )}
        <p className={`${true ? "py-2" : "py-1"}`}>Message...</p>
      </div>
    </div>
  );
};

export default ChatMessage;
