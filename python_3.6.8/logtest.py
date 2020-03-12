#!/usr/bin/env python3
import sys, traceback
import logging
import logging.handlers
import random
import pprint

logger = None

MESSAGES = [
    "Hello, World",
    "Goodbye Cruel World",
    "You had me at hello"
]

LEVELS = {'debug': logging.DEBUG,
          'info': logging.INFO,
          'warning': logging.WARNING,
          'error': logging.ERROR,
          'critical': logging.CRITICAL}

class OneLineExceptionFormatter(logging.Formatter):
    def formatException(self, exc_info):
        """
        Format an exception so that it prints on a single line.
        """
        result = super(OneLineExceptionFormatter, self).formatException(exc_info)
        return repr(result).replace('\\n', '#010') # or format into one line however you want to

    def format(self, record):
        z = super(OneLineExceptionFormatter, self).format(record)
        s = z.replace('\n', '#010') if record.exc_text else z
        return s


def configure_log():
    formatter = OneLineExceptionFormatter('%(module)s[%(process)s]: %(message)s')
    syslogHandler = logging.handlers.SysLogHandler(address=("127.0.0.1", 514))
    syslogHandler.setFormatter(formatter)
    logger = logging.getLogger(__name__)
    level_name = None
    if len(sys.argv) > 1:
        level_name = sys.argv[1]
    level = LEVELS.get(level_name, logging.NOTSET)
    logger.addHandler(syslogHandler)
    logger.setLevel(logging.DEBUG)
    return logger

def recursive(i):
    logger.info(f"i is {i}")
    if i>=10:
        logger.critical(f"i is too big: {i} {random.choice(MESSAGES)}")
    elif i==2:
        if random.choice([True, False]):
            logger.warning(f"i is not that big: {i} {random.choice(MESSAGES)}")
            raise ValueError('Wow, TWO!')
    else:
        tmp = random.choice([True, False, True, True])
        if tmp:
            logger.warning(f"going to push i: {i} {random.choice(MESSAGES)}")
            recursive(i+1)
        else:
            logger.info(f"{i} {random.choice(MESSAGES)}")
    return


if __name__=='__main__':
    logger = configure_log()
    logger.info('Logging configured OK')
    #logger.info('This is an info message')
    #logger.warning('This is a warning message')
    #logger.error('This is an error message')
    #logger.critical('This is a critical error message')
    logger.warning('{"mydata": "val1", "mydata2": ["val2","val3"]}')
    try:
        recursive(0)
    except Exception as e:
        logger.exception(e)


