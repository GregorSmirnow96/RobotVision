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
        blurred_image)
    return tracked_motion

def blur_image(image):
    kernel_dimension = 5
    kernel = numpy.ones(
        (kernel_dimension, kernel_dimension),
        numpy.float32) / (kernel_dimension * kernel_dimension)
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
        10,
        63,
        cv2.THRESH_BINARY)
    blurred_threshold = blur_image(low_threshold)
    _, high_threshold = cv2.threshold(
        blurred_threshold,
        0,
        51,
        cv2.THRESH_BINARY)
    return high_threshold
    
def get_contours(grayscale_image):
    contours, _ = cv2.findContours(
        grayscale_image,
        cv2.RETR_EXTERNAL,
        cv2.CHAIN_APPROX_SIMPLE)
    large_contours = []
    for contour in contours:
        if (cv2.contourArea(contour) > 80):
            large_contours.append(contour)
    return contours

def draw_contours(
    image,
    contours):
    new_image = image.copy()
    cv2.drawContours(
        new_image,
        contours,
        -1,
        (255, 0, 0),
        1)
    return new_image

def draw_bounding_boxes(
    image,
    contours):
    new_image = image.copy()
    for contour in contours:
        x, y, w, h = cv2.boundingRect(contour)
        area = w * h
        if (area > 4000):
            new_image = cv2.rectangle(
                new_image,
                (x, y),
                (x + w, y + h),
                (255, 255, 0),
                2)
    return new_image


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
    contours = get_contours(thresholded_grayscale_image)
    image_with_contours = draw_contours(
        thresholded_grayscale_image,
        contours)
    image_with_bounding_boxes = draw_bounding_boxes(
        image,
        contours)

    
    cv2.imshow(
        "Tracked Movement",
        image_with_bounding_boxes)
    cv2.imshow(
        "Thresholded Grayscale",
        thresholded_grayscale_image)
    cv2.imshow(
        "Contours",
        image_with_contours)

    selectedKeyID = cv2.waitKey(1)
    if selectedKeyID == escape_key_id:
        break

cv2.destroyAllWindows()