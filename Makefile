run: exportlib exec

exportlib:
	export LD_LIBRARY_PATH=$LD_LIBRARY_PATH:/home/astefu/Documents/Roguelike/lib/BearLibTerminal/Linux64

exec:
	./build/bin/native/releaseExecutable/Roguelike.kexe
