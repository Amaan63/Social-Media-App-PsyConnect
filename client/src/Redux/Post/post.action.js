import {
  CREATE_COMMENT_FAILURE,
  CREATE_COMMENT_REQUEST,
  CREATE_COMMENT_SUCCESS,
  CREATE_POST_FAILURE,
  CREATE_POST_REQUEST,
  CREATE_POST_SUCCESS,
  GET_ALL_POST_FAILURE,
  GET_ALL_POST_REQUEST,
  GET_ALL_POST_SUCCESS,
  GET_USERS_POST_FAILURE,
  GET_USERS_POST_REQUEST,
  GET_USERS_POST_SUCCESS,
  LIKE_COMMENT_FAILURE,
  LIKE_COMMENT_REQUEST,
  LIKE_COMMENT_SUCCESS,
  LIKE_POST_FAILURE,
  LIKE_POST_REQUEST,
  LIKE_POST_SUCCESS,
} from "./post.actionType";
import { api, API_BASE_URL } from "../../config/api";

export const createPostAction = (postData) => async (dispatch) => {
  dispatch({ type: CREATE_POST_REQUEST });
  try {
    const { data } = await api.post(
      `${API_BASE_URL}/private/posts/createPost`,
      postData
    );
    dispatch({ type: CREATE_POST_SUCCESS, payload: data });
    console.log("Created Post ", data);
  } catch (error) {
    console.log("error ", error);
    dispatch({ type: CREATE_POST_FAILURE, payload: error });
  }
};

export const getAllPostAction = () => async (dispatch) => {
  dispatch({ type: GET_ALL_POST_REQUEST });
  try {
    const { data } = await api.get(`${API_BASE_URL}/private/posts/allPosts`);
    dispatch({ type: GET_ALL_POST_SUCCESS, payload: data });
    console.log("GET All Posts DATA ---  ", data);
  } catch (error) {
    console.log("error ", error);
    dispatch({ type: GET_ALL_POST_FAILURE, payload: error });
  }
};

export const getUsersPostAction = (userId) => async (dispatch) => {
  dispatch({ type: GET_USERS_POST_REQUEST });
  try {
    const { data } = await api.get(
      `${API_BASE_URL}/private/posts/user/${userId}`
    );
    dispatch({ type: GET_USERS_POST_SUCCESS, payload: data });
    console.log("GET Users Posts DATA ---  ", data);
  } catch (error) {
    console.log("error ", error);
    dispatch({ type: GET_USERS_POST_FAILURE, payload: error });
  }
};

export const likePostAction = (postId) => async (dispatch) => {
  dispatch({ type: LIKE_POST_REQUEST });
  try {
    const { data } = await api.put(
      `${API_BASE_URL}/private/posts/likePost/${postId}`
    );
    dispatch({ type: LIKE_POST_SUCCESS, payload: data });
    console.log("Like Posts Success ---  ", data);
  } catch (error) {
    console.log("error ", error);
    dispatch({ type: LIKE_POST_FAILURE, payload: error });
  }
};

//Create Comment
export const createCommentAction = (reqData) => async (dispatch) => {
  dispatch({ type: CREATE_COMMENT_REQUEST });
  try {
    const { data } = await api.post(
      `${API_BASE_URL}/private/comments/createComment/post/${reqData.postId}`,
      reqData.data
    );
    dispatch({ type: CREATE_COMMENT_SUCCESS, payload: data });
    console.log("Created Comment ", data);
  } catch (error) {
    console.log("error ", error);
    dispatch({ type: CREATE_COMMENT_FAILURE, payload: error });
  }
};

// Like COmment
export const likeCommentAction = (commentId) => async (dispatch) => {
  dispatch({ type: LIKE_COMMENT_REQUEST });
  try {
    const { data } = await api.put(
      `${API_BASE_URL}/private/comments/likeComment/${commentId}`
    );
    dispatch({ type: LIKE_COMMENT_SUCCESS, payload: data });
    console.log("Like Comment Success ---  ", data);
  } catch (error) {
    console.log("error ", error);
    dispatch({ type: LIKE_COMMENT_FAILURE, payload: error });
  }
};
