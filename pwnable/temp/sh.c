#include <stdio.h>
#include <stdlib.h>
int main(int argc, char** argv)
{
	char str[10];
	gets(str);
	printf("%s\n",str);
	system("/bin/sh");
	printf("next\n");
	return 0;
}
