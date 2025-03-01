import "./button.css";

function Button({
  children,
  outline = false,
  disabled = false,
  className = "",
  width = null,
}) {
  const classNames = [
    "button",
    outline ? "button--outline" : "",
    className,
  ].filter(Boolean);
  const style = width ? { width: `${width}px` } : undefined;

  return (
    <button
      className={classNames.join(" ")}
      disabled={disabled}
      style={style}
    >
      {children}
      <svg
        className="button__icon"
        version="1.1"
        xmlns="http://www.w3.org/2000/svg"
        xmlnsXlink="http://www.w3.org/1999/xlink"
        viewBox="0 0 490 490"
      >
        <polygon
          points="240.112,0 481.861,245.004 240.112,490 8.139,490 250.29,245.004 8.139,0"
          fill="currentColor"
        />
      </svg>
    </button>
  );
}

export default Button;
