import React from "react";
import { useSelector } from "react-redux";

const ChatMessage = ({ item }) => {
  const { auth } = useSelector((store) => store);
  if (!item) return null;

  const isReqUserMessage =
    auth?.user?.id && item?.user?.id && auth.user.id === item.user.id;

  return (
    <div
      className={`flex ${
        isReqUserMessage ? "justify-end" : "justify-start"
      } text-white`}
    >
      <div
        className={`p-1 ${item.image ? "rounded-md" : "px-5 rounded-full"} ${
          isReqUserMessage ? "bg-[#1db954]" : "bg-[#3a3f54]"
        }`} // ✅ Different background colors
      >
        {item.image && (
          <img
            className="w-[12rem] h-[17rem] object-cover rounded-md"
            alt="Message"
            src={item.image}
          />
        )}
        {item.content && (
          <p className={`${item.image ? "py-2" : "py-1"}`}>{item.content}</p>
        )}
      </div>
    </div>
  );
};

export default ChatMessage;
