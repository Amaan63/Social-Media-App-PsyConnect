const cloud_name = import.meta.env.VITE_CLOUDINARY_CLOUD_NAME;
const upload_preset = import.meta.env.VITE_CLOUDINARY_UPLOAD_PRESET;
const baseUrl = import.meta.env.VITE_CLOUDINARY_BASE_URL;

export const uploadToCloudinary = async (file, fileType) => {
  if (file && fileType) {
    const data = new FormData();
    data.append("file", file);
    data.append("upload_preset", upload_preset);
    data.append("cloud_name", cloud_name);

    const res = await fetch(`${baseUrl}/${cloud_name}/${fileType}/upload`, {
      method: "post",
      body: data,
    });
    console.log("res-----", res);
    const fileData = await res.json();
    console.log("Url------", fileData.url);
    return fileData.url;
  } else {
    console.log("Error...........");
  }
};
