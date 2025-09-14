from cryptography.hazmat.primitives.ciphers import Cipher, algorithms, modes
import os

# Encrypting ...
def encrypt_file(infile, outfile, key):
    iv = os.urandom(16)  # 16 bytes
    cipher = Cipher(algorithms.AES(key), modes.CFB(iv))
    encryptor = cipher.encryptor()
    with open(infile, 'rb') as f:
        data = f.read()
    encrypted = encryptor.update(data) + encryptor.finalize()

    with open(outfile, 'wb') as f:
        f.write(iv + encrypted)

    print(f"Encrypted → {outfile}")

# Decrypting ...
def decrypt_file(infile, outfile, key):
    with open(infile, 'rb') as f:
        iv = f.read(16)
        encrypted = f.read()

    cipher = Cipher(algorithms.AES(key), modes.CFB(iv))
    decryptor = cipher.decryptor()

    decrypted = decryptor.update(encrypted) + decryptor.finalize()

    with open(outfile, 'wb') as f:
        f.write(decrypted)

    print(f"Decrypted → {outfile}")

# --- Main ---
if __name__ == "__main__":
    key = b"0123456789abcdef"

    choice = input("Encrypt or Decrypt? (e/d): ").lower()
    if choice == 'e':
        infile = input("Input file: ").strip('"')
        outfile = input("Output file: ").strip('"')
        encrypt_file(infile, outfile, key)
    elif choice == 'd':
        infile = input("Input file: ").strip('"')
        outfile = input("Output file: ").strip('"')
        decrypt_file(infile, outfile, key)
    else:
        print("Invalid choice")
