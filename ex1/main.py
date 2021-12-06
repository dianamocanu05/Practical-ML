# import numpy as np
# import math
#
#
# def data_generator(count, a, b):
#     file = open("data.txt", "a")
#     for it in range(count):
#         w = np.random.normal(0.0, 1.0)  # gaussian noise
#         x = np.random.randint(0, 6)
#         y = a * math.sin(b * x) + w
#         file.write(str(x) + " " + str(y) + "\n")
#     file.close()
#
#
# def get_data():
#     file = open("data.txt", "r")
#     lines = file.readlines()
#     x = []
#     y = []
#     for line in lines:
#         bits = line.split()
#         x.append(float(bits[0]))
#         y.append(float(bits[1]))
#     return x, y
#
#
# def cost_calculator(thetas, x, y):
#     m = len(y)
#     predictions = x.dot(thetas)
#     cost = (1 / 2 * m) * np.sum(np.square(predictions - y))
#     return cost
#
#
# def gradient_descent(x, y, theta, learning_rate, iterations=100):
#     m = len(y)
#     cost_history = np.zeros(iterations)
#     theta_history = np.zeros((iterations, 2))
#     for iteration in range(iterations):
#         prediction = np.dot(x, theta)
#         theta = theta - (1 / m) * learning_rate * (x.T.dot((prediction - y)))
#         theta_history[iteration, :] = theta.T
#         cost_history[iteration] = cost_calculator(theta, x, y)
#     return theta, cost_history, theta_history
#
#
# if __name__ == "__main__":
#     data_generator(100, 2, 3)
#     x, y = get_data()
#     learning_rate = 0.01
#     NB_ITERATIONS = 100
#
#     theta = np.random.randn(2, 1)
#     x_b = np.c_[np.ones((len(x), 1)), x]
#     theta, cost_history, theta_history = gradient_descent(x_b, y, theta, NB_ITERATIONS)
#
#     theta0 = theta[0][0]
#     theta1 = theta[1][0]
#     mse = cost_history[-1]
#
#     print("Theta0 : (:0.3f),\nTheta1 : (:0.3f)".format(theta0, theta1))
#     print("Mean squared error : (:0.3f)".format(mse))


