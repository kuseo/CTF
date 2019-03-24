import cv2 as cv
import numpy as np
path = 'qrmaster.jpg'

img = cv.imread(path, 1)

height = img.shape[0]
width = img.shape[1]

nimg = np.zeros((height, width, 3), np.uint8)

for y in range(0, height):
    for x in range(0, width):
        color = img[y,x]
        if color[0] == 206 and color[0] == color[1] and color[1] == color[2]:
            nimg[y, x] = [0, 0, 0]
        else:
            nimg[y, x] = color
    


cv.imwrite('newimg.jpg', nimg)
cv.imshow('title', nimg)
cv.waitKey(0)
