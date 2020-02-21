# This dockerfile uses the gradle image
# VERSION 1
# Author: wade

FROM gradle

MAINTAINER wade wadechen@outlook.com

RUN git clone https://github.com/wadeee/qzyz-contact.git ~/qzyz-contact\
  && cd ~/qzyz-contact\
  && echo y | cp contact.xlsx /media/\
  && gradle build

CMD cd ~/qzyz-contact\
  && gradle bootRun
