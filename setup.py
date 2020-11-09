import setuptools

setuptools.setup(name='yelp-data-analysis',
                 version='0.1',
                 description='yelp analysis',
                 url='https://github.com/data-kata/yelp-data-analysis-project',
                 author='Karven',
                 author_email='',
                 license='MIT',
                 packages=['functions', 'util'],  # setuptools.find_packages(),
                 setup_requires=["pytest-runner"],
                 tests_require=["pytest"])
