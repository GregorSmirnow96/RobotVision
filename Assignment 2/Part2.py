import cv2
import numpy

# Global variables

escape_key_id = 27


# Utility methods

def get_time_weighted_average_of_image(
    image,
    weighted_average):
    blurred_image = blur_image(image)
    cv2.accumulateWeighted(
        blurred_image,
        weighted_average,
        0.32)
    tracked_motion = cv2.convertScaleAbs(weighted_average)
    tracked_motion = cv2.absdiff(
        tracked_motion,
        image)
    return tracked_motion

def blur_image(image):
    kernel = numpy.ones(
        (7, 7),
        numpy.float32) / 49
    blurred_image = cv2.filter2D(
        image,
        -1,
        kernel)
    return blurred_image

def get_thresholded_grayscale_image(image):
    tracked_motion = cv2.cvtColor(
        image,
        cv2.COLOR_BGR2GRAY)
    _, low_threshold = cv2.threshold(
        tracked_motion,
        8,
        24,
        cv2.THRESH_BINARY)
    blurred_threshold = blur_image(low_threshold)
    _, high_threshold = cv2.threshold(
        blurred_threshold,
        22,
        24,
        cv2.THRESH_BINARY)
    return high_threshold
    
def draw_contours(grayscale_image):
    contours, _ = cv2.findContours(
        grayscale_image,
        cv2.RETR_EXTERNAL,
        cv2.CHAIN_APPROX_SIMPLE)
    cv2.drawContours(
        grayscale_image,
        contours,
        -1,
        (255, 0, 0),
        1)


# Program starts here

video_feed = cv2.VideoCapture(0)
status, image = video_feed.read()
weighted_average = numpy.float32(image)

while True:
    _, image = video_feed.read()
    
    time_weighted_average = get_time_weighted_average_of_image(
        image,
        weighted_average)
    thresholded_grayscale_image = get_thresholded_grayscale_image(
        time_weighted_average)
    draw_contours(thresholded_grayscale_image)
    
    
    cv2.imshow(
        "Tracked Movement",
        thresholded_grayscale_image)
    
    selectedKeyID = cv2.waitKey(1)
    if selectedKeyID == escape_key_id:
        break

cv2.destroyAllWindows()