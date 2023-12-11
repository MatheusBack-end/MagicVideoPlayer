#frame per frame
ffmpeg -i $1 -vf fps=12/1 ./frame-%04d.jpeg
