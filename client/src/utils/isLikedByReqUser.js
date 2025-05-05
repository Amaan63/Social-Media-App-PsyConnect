export const isLikedByReqUser = (reqUserID, post) => {
  for (let user of post.liked) {
    if (reqUserID === user.id) {
      return true;
    }
  }
  return false;
};
