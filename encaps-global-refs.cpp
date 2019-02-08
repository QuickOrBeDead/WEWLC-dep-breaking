bool AGG230_activeframe[AGG230_SIZE];
bool AGG230_suspendedframe[AGG230_SIZE];

void AGGController::suspend_frame() {
  frame_copy(AGG230_suspendedframe, AGG230_activeframe);
  clear(AGG230_activeframe);
  flush_frame_buffers();
}

void AGGController::flush_frame_buffers() {
  for (int n = 0; n < AGG230_SIZE; ++n) {
    AGG230_activeframe[n] = false;
    AGG230_suspendedframe[n] = false;
  }
}

/* In this example, we have some code that does work with a few global arrays. The suspend_frame method needs to access the active and suspended frames. At first glance, it looks like we can make the frames members of the AGGController class, but some other classes (not shown) use the frames. What can we do? */

/* One immediate thought is that we can pass them as parameters to the suspend_frame method using Parameterize Method (383), but after we do that, we’ll have to pass them as parameters to any methods that suspend_frame calls that use them as globals. In this case, flush_frame_buffer is an offender. */

/* The next option is to pass both frames as constructor arguments to AGGController. We could do that, but it is worth taking a look at other places where they are used. If it seems that whenever we use one we are also using the other, we could bundle them together. */
